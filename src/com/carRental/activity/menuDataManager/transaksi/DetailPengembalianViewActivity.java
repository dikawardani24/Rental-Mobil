package com.carRental.activity.menuDataManager.transaksi;

import com.carRental.model.Car;
import com.carRental.model.Pengembalian;
import com.carRental.model.Sewa;
import com.dika.activity.InputActivity;
import com.dika.util.CalendarHelper;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextField;
import com.dika.view.component.custom.CurrencyFormattedTextField;
import com.dika.view.component.custom.DecimalFormattedTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class DetailPengembalianViewActivity extends InputActivity<DetailPengembalianView>
    implements DetailPengembalianView {
    private final DetailPengembalianView view = new DetailPengembalianViewImpl();

    public void setPengembalian(@NotNull Pengembalian pengembalian) {
        SwingUtilities.invokeLater(() -> {
            getIdPengembalianField().setText(String.valueOf(pengembalian.getId()));

            CalendarHelper helper = CalendarHelper.INSTANCE;
            getTglKembaliField().setText(helper.dateToLocal(pengembalian.getTglTransaksi()));

            int overtime = pengembalian.getOvertime();
            getOvertimeField().setValue(overtime);

            Sewa sewa = pengembalian.getSewa();
            Car car = sewa.getCar();
            getBiayaOvertimeField().setValue(overtime*car.getHargaSewa());
            getTotalTagihanField().setValue(sewa.getTotalTagihan() + overtime);
        });
    }

    @NotNull
    @Override
    public DetailPengembalianView getView() {
        return view;
    }

    @Override
    protected void initListener(DetailPengembalianView detailPengembalianView) {
        getCloseButton().addActionListener(e -> stop());
    }

    @Override
    public TextField getIdPengembalianField() {
        return view.getIdPengembalianField();
    }

    @Override
    public TextField getTglKembaliField() {
        return view.getTglKembaliField();
    }

    @Override
    public DecimalFormattedTextField getOvertimeField() {
        return view.getOvertimeField();
    }

    @Override
    public CurrencyFormattedTextField getBiayaOvertimeField() {
        return view.getBiayaOvertimeField();
    }

    @Override
    public CurrencyFormattedTextField getTotalTagihanField() {
        return view.getTotalTagihanField();
    }

    @Override
    public Button getCloseButton() {
        return view.getCloseButton();
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
