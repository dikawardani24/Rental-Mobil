/*
 * Copyright 2018 dika.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.carRental.activity.main;

import com.carRental.Session;
import com.carRental.activity.MainActivity;
import com.carRental.activity.main.common.DetailCarActivity;
import com.carRental.activity.main.common.DetailPelangganActivity;
import com.carRental.activity.main.pengembalian.DetailCarContainer;
import com.carRental.activity.main.pengembalian.DetailPelangganContainer;
import com.carRental.activity.main.pengembalian.DetailTagihanContainer;
import com.carRental.model.Car;
import com.carRental.model.Pelanggan;
import com.carRental.model.Pengembalian;
import com.carRental.model.Sewa;
import com.carRental.service.CarServiceImpl;
import com.carRental.service.PengembalianServiceImpl;
import com.carRental.service.SewaServiceImpl;
import com.carRental.util.CarStatus;
import com.carRental.activity.tableModel.PengembalianTableModel;
import com.dika.Logger;
import com.dika.activity.Activity;
import com.dika.util.CalendarHelper;
import com.dika.util.NumberHelper;
import com.dika.view.component.Button;
import com.dika.view.component.FormattedTextField;
import com.dika.view.component.Panel;
import com.dika.view.component.Table;
import com.dika.view.component.TextField;
import com.dika.view.custom.PagingTableView;
import com.dika.view.custom.PagingTableViewAction;
import com.dika.view.custom.PagingTableViewService;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

/**
 *
 * @author dika
 */
public class PengembalianController extends MainController
        implements PengembalianContainer, PagingTableViewService {

    private final PengembalianContainer container;
    private PengembalianTableModel tableModel;
    private PagingTableViewAction pagingTableViewAction;
    private Sewa sewa;

    public PengembalianController(MainActivity mainActivity, PengembalianContainer container) {
        super(mainActivity);
        this.container = container;
    }

    private boolean validateIdSewa() {
        if (getIdSewaField().isEmpty()) {
            showNotifOn(getIdSewaField(), "ID Sewa Masih Kosong");
            return false;
        }

        try {
            Integer.parseInt(getIdSewaField().getText());
            return true;
        } catch (NumberFormatException e) {
            showNotifOn(getIdSewaField(), "ID Sewa Adalah Angka");
            return false;
        }
    }

    private boolean validateSewa() {
        if (sewa == null) {
            showNotifOn(getSearchButton(), "Tekan Tombol Ini Untuk Mencari Sewa");
            return false;
        }

        return true;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean validateAllInput() {
        return validateIdSewa() && validateSewa();
    }

    private void findSewa() {
        if (!validateIdSewa()) {
            return;
        }

        int idSewa = Integer.parseInt(getIdSewaField().getText());
        sewa = execute(new SewaServiceImpl(), sewaService -> {
            try {
                return sewaService.findBy(idSewa);
            } catch (Exception e) {
                return null;
            }
        });

        if (sewa == null) {
            showFailed("Tidak Menemukan Transaksi Sewa Dengan ID : " + idSewa);
            return;
        }

        if (isSewaCompleted(sewa)) {
            showInfo("Transaksi Sewa Ini Sudah Selesai");
            return;
        }

        viewDetail(sewa);
    }

    private boolean isSewaCompleted(@NotNull Sewa sewa) {
        Pengembalian pengembalian = execute(new PengembalianServiceImpl(), pengembalianService -> {
            try {
                return pengembalianService.findBy(sewa);
            } catch (Exception e) {
                return null;
            }
        });

        return pengembalian != null;
    }

    private void viewDetail(Sewa sewa) {
        viewDetail(sewa.getCar());
        viewDetail(sewa.getPelanggan());

        getLamaSewaField().setValue(sewa.getLamaSewa());
        viewTotalTagihan(sewa);
    }

    private void viewTotalTagihan(Sewa sewa) {
        CalendarHelper helper = CalendarHelper.INSTANCE;
        Date currentDate = helper.today();
        long overtime = helper.min(currentDate, sewa.getTglTransaksi());
        double totalTagihan;

        if (overtime > 0) {
            totalTagihan = (overtime * sewa.getCar().getHargaSewa()) + sewa.getTotalTagihan();
        } else {
            totalTagihan = sewa.getTotalTagihan();
            overtime = 0;
        }

        getOvertimeField().setValue(overtime);
        getTagihanField().setValue(totalTagihan);
    }

    private void viewDetail(Car car) {
        getNoPlatField().setText(car.getNoPlat());
        getHargaSewaField().setValue(car.getHargaSewa());
    }

    private void viewDetail(Pelanggan pelanggan) {
        getNamaPelangganField().setText(pelanggan.getNama());
        getNoHpField().setText(pelanggan.getNoHp());
    }

    private void viewMoreCarDetail() {
        if (!validateAllInput()) {
            return;
        }

        DetailCarActivity activity = startOther(DetailCarActivity.class);
        activity.setCar(sewa.getCar());
    }

    private void viewMorePelangganDetail() {
        if (!validateAllInput()) {
            return;
        }

        DetailPelangganActivity activity = startOther(DetailPelangganActivity.class);
        activity.setPelanggan(sewa.getPelanggan());
    }

    private void save() {
        if (!validateAllInput()) {
            return;
        }

        Pengembalian pengembalian = new Pengembalian();
        pengembalian.setOvertime(Integer.parseInt(getOvertimeField().getValue().toString()));
        pengembalian.setPenerimaKembali(Session.getInstance().getKaryawan());
        pengembalian.setSewa(sewa);
        pengembalian.setTglTransaksi(CalendarHelper.INSTANCE.today());

        execute(new PengembalianServiceImpl(), pengembalianService -> {
            try {
                pengembalianService.create(pengembalian);
                boolean carUpdated = updateStatus(sewa.getCar());

                if (carUpdated) {
                    pagingTableViewAction.refreshPage();
                    clear();
                    showSucceed("Data Pengembalian Berhasil Disimpan");
                } else {
                    showFailed("Data Pengembalian Gagal Disimpan");
                }
            } catch (Exception e) {
                Logger.INSTANCE.printError(e);
                showFailed("Data Pengembalian Gagal Disimpan");
            }

            return Unit.INSTANCE;
        });
    }

    private boolean updateStatus(Car car) {
        return execute(new CarServiceImpl(),
                carService -> {
                    try {
                        car.setStatus(CarStatus.ADA.getText());
                        carService.update(car);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                });
    }

    public void init() {
        getSeeDetailCarButton().addActionListener(e -> viewMoreCarDetail());

        getSeeDetailPelanggan().addActionListener(e -> viewMorePelangganDetail());

        getSearchButton().addActionListener(e -> findSewa());

        getClearButton().addActionListener(e -> clear());

        getSaveButton().addActionListener(e -> save());

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

        getDetailTagihanContainer().getCalculateButton().addActionListener(evt -> {
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


    private void initPagingTable() {
        Table table = getPagingTableView().getTable();
        table.setEditable(false);
        tableModel = new PengembalianTableModel(table);
        pagingTableViewAction = new PagingTableViewAction(this, getPagingTableView(), 100);
        pagingTableViewAction.toLastPage();
    }

    @Override
    public int countData() {
        return execute(new PengembalianServiceImpl(), service -> {
            CalendarHelper helper = CalendarHelper.INSTANCE;
            return service.countBy(helper.startOfToday(), helper.endOfToday());
        });
    }

    @Override
    public void insertData(int firstResult, int maxResult) {
        execute(new PengembalianServiceImpl(), service -> {
            CalendarHelper helper = CalendarHelper.INSTANCE;
            List<Pengembalian> pengembalians = service.findsBy(helper.startOfToday(), helper.endOfToday(),
                    firstResult, maxResult);
            tableModel.clear();
            tableModel.insert(pengembalians);
            return service;
        });
    }

    @Override
    public void clear() {
        getIdSewaField().clear();
        container.clear();
        sewa = null;
    }

    @Override
    public TextField getIdSewaField() {
        return container.getIdSewaField();
    }

    @Override
    public DetailCarContainer getDetailCarContainer() {
        return container.getDetailCarContainer();
    }

    @Override
    public DetailPelangganContainer getDetailPelangganContainer() {
        return container.getDetailPelangganContainer();
    }

    @Override
    public DetailTagihanContainer getDetailTagihanContainer() {
        return container.getDetailTagihanContainer();
    }

    @Override
    public PagingTableView getPagingTableView() {
        return container.getPagingTableView();
    }

    @Override
    public Button getSearchButton() {
        return container.getSearchButton();
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
