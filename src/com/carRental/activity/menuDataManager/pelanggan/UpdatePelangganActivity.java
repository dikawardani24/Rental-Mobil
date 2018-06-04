package com.carRental.activity.menuDataManager.pelanggan;

import com.carRental.activity.menuDataManager.PelangganManagerActivity;
import com.carRental.model.Pelanggan;
import com.carRental.service.PelangganServiceImpl;
import com.dika.Logger;
import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.view.component.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class UpdatePelangganActivity extends InputActivity<UpdatePelangganView> implements UpdatePelangganView {
    private final UpdatePelangganView view = new UpdatePelangganViewImpl();
    private Pelanggan pelanggan;

    public void setPelanggan(Pelanggan pelanggan) {
        SwingUtilities.invokeLater(() -> {
            this.pelanggan = pelanggan;
            getNamaField().setText(pelanggan.getNama());
            getAlamatField().setText(pelanggan.getAlamat());
            getJenisKelaminComboBox().setSelectedItem(pelanggan.getJenisKelamin());
            getNoKtpField().setText(pelanggan.getNoKtp());
            getNoHpField().setText(pelanggan.getNoHp());
        });
    }

    private void updatePelanggan() {
        if (!validateInput()) return;

        try {
            Pelanggan old = (Pelanggan) pelanggan.clone();

            pelanggan.setNama(getNamaField().getText());
            pelanggan.setNoKtp(getNoKtpField().getText());
            pelanggan.setNoHp(getNoHpField().getText());
            pelanggan.setJenisKelamin(String.valueOf(getJenisKelaminComboBox().getSelectedItem()));
            pelanggan.setAlamat(getAlamatField().getText());

            if (old.equals(pelanggan)) {
                showInfo("Tidak ada perubahan data dilakukan");
                return;
            }

            execute(new PelangganServiceImpl(), "Data Pelanggan Berhasil Disimpan",
                    "Data Pelanggan Gagal Disimpan",
                    service -> {
                        service.update(pelanggan);
                        Activity<?> parent = getParent();
                        if (parent != null && parent instanceof PelangganManagerActivity) {
                            ((PelangganManagerActivity) parent).refresh();
                        }
                        return service;
                    });
        } catch (CloneNotSupportedException e) {
            Logger.INSTANCE.printError(e);
        }
    }

    @Override
    protected void initListener(UpdatePelangganView updatePelangganView) {
        getSaveButton().addActionListener(e -> updatePelanggan());
        getCancelButton().addActionListener(e -> stop());
        getClearButton().addActionListener(e -> clear());
    }

    @Override
    protected boolean validateInput() {
        if (pelanggan == null) {
            showFailed("Tidak ada data pelanggan yang akan diupdate");
            return false;
        }
        return super.validateInput();
    }

    @Override
    public TextField getNamaField() {
        return view.getNamaField();
    }

    @Override
    public TextArea getAlamatField() {
        return view.getAlamatField();
    }

    @Override
    public ComboBox<String> getJenisKelaminComboBox() {
        return view.getJenisKelaminComboBox();
    }

    @Override
    public TextField getNoKtpField() {
        return view.getNoKtpField();
    }

    @Override
    public TextField getNoHpField() {
        return view.getNoHpField();
    }

    @Override
    public Button getClearButton() {
        return view.getClearButton();
    }

    @Override
    public Button getCancelButton() {
        return view.getCancelButton();
    }

    @Override
    public Button getSaveButton() {
        return view.getSaveButton();
    }

    @NotNull
    @Override
    public UpdatePelangganView getView() {
        return view;
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
