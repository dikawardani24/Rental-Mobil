package com.carRental.activity.main.sewa;

import com.carRental.Session;
import com.carRental.activity.MainActivity;
import com.carRental.activity.main.DetailCarActivity;
import com.carRental.activity.main.MainController;
import com.carRental.model.Car;
import com.carRental.model.Pelanggan;
import com.carRental.model.Sewa;
import com.carRental.report.Kwitansi;
import com.carRental.service.CarServiceImpl;
import com.carRental.service.PelangganServiceImpl;
import com.carRental.service.SewaServiceImpl;
import com.carRental.util.CarStatus;
import com.carRental.activity.tableModel.SewaTableModel;
import com.dika.Logger;
import com.dika.activity.Activity;
import com.dika.util.CalendarHelper;
import com.dika.util.NumberHelper;
import com.dika.view.component.Button;
import com.dika.view.component.FormattedTextField;
import com.dika.view.component.Panel;
import com.dika.view.component.Table;
import com.dika.view.custom.PagingTableView;
import com.dika.view.custom.PagingTableViewAction;
import com.dika.view.custom.PagingTableViewService;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public class SewaController extends MainController 
        implements SewaContainer, PagingTableViewService {

    private final SewaContainer container;
    private SewaTableModel tableModel;
    private Car chosenCar;
    private Pelanggan chosenPelanggan;
    private PagingTableViewAction pagingTableViewAction;

    public SewaController(MainActivity mainActivity, SewaContainer container) {
        super(mainActivity);
        this.container = container;
    }

    public void init() {
        getSaveButton().addActionListener(e -> save());

        getClearButton().addActionListener(e -> clear());

        getChooseCarButton().addActionListener(e -> chooseCar());

        getSeeDetailCarButton().addActionListener(e -> viewDetailCar());

        getSearchlPelangganButton().addActionListener(e -> findPelanggan());

        getLamaSewaField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                CountTagihan countTagihan = new CountTagihan();
                countTagihan.start();
            }
        });

        getPaidField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                CountKembalianThread thread = new CountKembalianThread();
                thread.start();
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                double dibayar = NumberHelper.INSTANCE.toDouble(getPaidField().getText());
                getPaidField().setValue(dibayar);
            }
        });

        getCalculatorButton().addActionListener(evt -> {
            if (getPaidField().isEmpty()) {
                showEmptyNotifOn(getPaidField());
                return;
            }

            double totalTagihan = NumberHelper.INSTANCE.toDouble(getTagihanField().getValue());

            double dibayar = NumberHelper.INSTANCE.toDouble(getPaidField().getValue());
            if (dibayar < totalTagihan) {
                showNotifOn(getPaidField(), "Jumlah yang dibayar masih kurang");
                return;
            }

            countKembalian(dibayar);
        });

        initPagingTable();
    }

    private void save() {
        if (!validateAllInput()) {
            return;
        }

        if (updateStatus(chosenCar)) {
            Sewa sewa = new Sewa();
            initData(sewa);
            execute(new SewaServiceImpl(), "Data Sewa Berhasil Disimpan", "Data Sewa Gagal Disimpan",
                    sewaService -> {
                        sewaService.create(sewa);
                        pagingTableViewAction.toLastPage();
                        printKwitansi(sewa);
                        return Unit.INSTANCE;
                    });
        } else {
            showFailed("Gagal Mengupdate Status Mobil Menjadi "+CarStatus.SEDANG_DISEWA.getText());
        }

    }

    private boolean updateStatus(Car car) {
        return execute(new CarServiceImpl(), carService -> {
            try {
                car.setStatus(CarStatus.SEDANG_DISEWA.getText());
                carService.update(car);
                return true;
            } catch (Exception e) {
                Logger.INSTANCE.printError(e);
                return false;
            }
        });
    }

    private void printKwitansi(Sewa sewa) {
        Kwitansi kwitansi = new Kwitansi(sewa);
        showReport(kwitansi);
    }

    private void initData(Sewa sewa) {
        sewa.setCar(chosenCar);
        sewa.setPelanggan(chosenPelanggan);
        sewa.setLamaSewa(NumberHelper.INSTANCE.toInt(getLamaSewaField().getValue()));
        sewa.setPemberiSewa(Session.getInstance().getKaryawan());
        sewa.setTglTransaksi(CalendarHelper.INSTANCE.today());
        sewa.setTotalTagihan(NumberHelper.INSTANCE.toDouble(getTagihanField().getValue()));
    }

    private void chooseCar() {
        ChooseCarActivity activity = startOther(ChooseCarActivity.class);
        activity.setOnChosenCar(car -> {
            chosenCar = car;
            getNoPlatField().setText(car.getNoPlat());
            getHargaSewaField().setValue(car.getHargaSewa());
        });
    }

    private void viewDetailCar() {
        if (chosenCar == null) {
            showInfo("Tidak ada mobil terpilih");
            return;
        }

        DetailCarActivity activity = startOther(DetailCarActivity.class);
        activity.setCar(chosenCar);
    }

    private void findPelanggan() {
        String noKtp = getNoKtpField().getText();
        
        chosenPelanggan = execute(new PelangganServiceImpl(), pelangganService -> {
            try {
                return pelangganService.findBy(noKtp);
            } catch (Exception e) {
                Logger.INSTANCE.printError(e);
                return null;
            }
        });

        if (chosenPelanggan != null) {
            getNamaPelangganField().setText(chosenPelanggan.getNama());
            getNoTelpField().setText(chosenPelanggan.getNoHp());
        } else {
            getInputPelangganContainer().clear();
            showFailed("Tidak Menemukan Pelanggan Dengan No. KTP : "+noKtp);
        }
    }

    private boolean validateAllInput() {
        return validateDataCar() && validateDataPelanggan()
                && validateTagihan();
    }

    private boolean validateTagihan() {
        int lamaSewa = NumberHelper.INSTANCE.toInt(getLamaSewaField().getValue());

        if (lamaSewa <= 0) {
            showNotifOn(getLamaSewaField(), "Lama sewa tidak valid");
            return false;
        }

        return true;
    }

    private boolean validateDataCar() {
        if (chosenCar == null) {
            showNotifOn(getChooseCarButton(), "Pilih mobil terlebih dahulu");
            return false;
        }

        return true;
    }

    private boolean validateDataPelanggan() {
        if (chosenPelanggan == null) {
            showNotifOn(getNoKtpField(), "Cari pelanggan dahulu");
            return false;
        }

        return true;
    }

    private void initPagingTable() {
        Table table = getPagingTableView().getTable();
        table.setEditable(false);
        tableModel = new SewaTableModel(table);
        pagingTableViewAction = new PagingTableViewAction(this, getPagingTableView(), 100);
        pagingTableViewAction.toLastPage();
    }

    @Override
    public void clear() {
        container.clear();
        chosenCar = null;
        chosenPelanggan = null;
    }

    @Override
    public int countData() {
        return execute(new SewaServiceImpl(), service -> {
            CalendarHelper helper = CalendarHelper.INSTANCE;
            return service.countBy(helper.startOfToday(), helper.endOfToday());
        });
    }

    @Override
    public void insertData(int firstResult, int maxResult) {
        execute(new SewaServiceImpl(), service -> {
            CalendarHelper helper = CalendarHelper.INSTANCE;
            List<Sewa> sewas = service.findsBy(helper.startOfToday(), helper.endOfToday(),
                    firstResult, maxResult);
            tableModel.clear();
            tableModel.insert(sewas);
            return service;
        });
    }

    private void countKembalian(double dibayar) {
        if (getTagihanField().isEmpty()) {
            return;
        }

        double totalTagihan = NumberHelper.INSTANCE.toDouble(getTagihanField().getValue());
        if (dibayar < totalTagihan) {
            getKembalianField().setValue(0);
            return;
        }

        double kembalian = dibayar - totalTagihan;
        getKembalianField().setValue(kembalian);
    }

    private class CountTagihan extends CountThread {

        private CountTagihan() {
            super(getLamaSewaField());
        }

        @Override
        protected void count() {
            int lamaSewa = NumberHelper.INSTANCE.toInt(lastEdit);

            if (chosenCar != null) {
                double tagihan = lamaSewa * chosenCar.getHargaSewa();
                getTagihanField().setValue(tagihan);
            } else {
                getTagihanField().setValue(0);
            }
        }
    }

    private class CountKembalianThread extends CountThread {

        private CountKembalianThread() {
            super(getPaidField());
        }

        @Override
        protected void count() {
            double dibayar = NumberHelper.INSTANCE.toDouble(getPaidField().getText());
            countKembalian(dibayar);
        }
    }

    private abstract class CountThread extends Thread {

        String lastEdit;

        private final FormattedTextField formattedTextField;

        private CountThread(FormattedTextField formattedTextField) {
            this.lastEdit = formattedTextField.getValue().toString();
            this.formattedTextField = formattedTextField;
            this.formattedTextField.setText(lastEdit);
        }

        @Override
        public void run() {
            formattedTextField.setText(formattedTextField.getValue().toString());
            while (formattedTextField.hasFocus()) {
                if (lastEdit.equals(formattedTextField.getText())) {
                    continue;
                }

                lastEdit = formattedTextField.getText();
                count();
            }
        }

        protected abstract void count();
    }

    @Override
    public InputCarContainer getInputCarContainer() {
        return container.getInputCarContainer();
    }

    @Override
    public InputPelangganContainer getInputPelangganContainer() {
        return container.getInputPelangganContainer();
    }

    @Override
    public TagihanContainer getTagihanContainer() {
        return container.getTagihanContainer();
    }

    @Override
    public PagingTableView getPagingTableView() {
        return container.getPagingTableView();
    }

    @Override
    public Button getSaveButton() {
        return container.getSaveButton();
    }

    @Override
    public Button getClearButton() {
        return container.getClearButton();
    }

    @Override
    public Panel getRoot() {
        return container.getRoot();
    }

    @NotNull
    @Override
    public Activity<?> getActivity() {
        return getMainActivity();
    }
}
