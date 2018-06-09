package com.carRental.activity.menuDataManager.transaksi;

import com.carRental.model.Karyawan;
import com.dika.activity.InputActivity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextArea;
import com.dika.view.component.TextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class DetailKaryawanActivity extends InputActivity<DetailKaryawanView> implements DetailKaryawanView {
    private final DetailKaryawanView view = new DetailKaryawanViewImpl();

    public void setKaryawan(Karyawan karyawan) {
        SwingUtilities.invokeLater(() -> {
            getIdKaryawanField().setText(String.valueOf(karyawan.getId()));
            getNamaField().setText(karyawan.getNama());
            getJenkelField().setText(karyawan.getJenisKelamin());
            getNoTelpField().setText(karyawan.getNoHp());
            getNoKtpField().setText(karyawan.getNoKtp());
            getAlamatField().setText(karyawan.getAlamat());
        });
    }

    @Override
    protected void initListener(DetailKaryawanView v) {
        getCancelButton().addActionListener(evt -> stop());
    }

    @NotNull
    @Override
    public DetailKaryawanView getView() {
        return view;
    }

    @Override
    public TextField getIdKaryawanField() {
        return view.getIdKaryawanField();
    }

    @Override
    public TextField getNamaField() {
        return view.getNamaField();
    }

    @Override
    public TextField getJenkelField() {
        return view.getJenkelField();
    }

    @Override
    public TextField getNoTelpField() {
        return view.getNoTelpField();
    }

    @Override
    public TextField getNoKtpField() {
        return view.getNoKtpField();
    }

    @Override
    public TextArea getAlamatField() {
        return view.getAlamatField();
    }

    @Override
    public Button getCancelButton() {
        return view.getCancelButton();
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
