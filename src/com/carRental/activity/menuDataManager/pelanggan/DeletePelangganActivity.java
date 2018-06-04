package com.carRental.activity.menuDataManager.pelanggan;

import com.carRental.activity.menuDataManager.PelangganManagerActivity;
import com.carRental.model.Pelanggan;
import com.carRental.service.PelangganServiceImpl;
import com.dika.Logger;
import com.dika.activity.Activity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextArea;
import com.dika.view.component.TextField;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class DeletePelangganActivity extends Activity<DeletePelangganView> implements DeletePelangganView{
    private final DeletePelangganView view = new DeletePelangganViewImpl();
    private Pelanggan pelanggan;

    public void setPelanggan(Pelanggan pelanggan) {
        SwingUtilities.invokeLater(() -> {
            this.pelanggan = pelanggan;
            getNamaField().setText(pelanggan.getNama());
            getAlamatField().setText(pelanggan.getAlamat());
            getJenisKelaminField().setText(pelanggan.getJenisKelamin());
            getNoHpField().setText(pelanggan.getNoHp());
            getNoKtpField().setText(pelanggan.getNoKtp());
        });
    }

    private void deletePelanggan() {
        if (pelanggan == null) {
            showInfo("Tidak ada data pelanggan yang akan dihapus");
            return;
        }

        execute(new PelangganServiceImpl(), service -> {
            try {
                service.destroy(pelanggan);
                Activity<?> parent = getParent();
                if (parent != null && parent instanceof PelangganManagerActivity) {
                    ((PelangganManagerActivity) parent).refresh();
                }
                showSucceed("Data Pelanggan Berhasil Dihapus");
                stop();
            } catch (Exception e) {
                Logger.INSTANCE.printError(e);
                showFailed("Data Pelanggan Gagal Dihapus");
            }
            return Unit.INSTANCE;
        });
    }

    @Override
    protected void initListener(DeletePelangganView deletePelangganView) {
        getCancelButton().addActionListener(e -> stop());
        getDeleteButton().addActionListener(e -> deletePelanggan());
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
    public TextField getJenisKelaminField() {
        return view.getJenisKelaminField();
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
    public Button getCancelButton() {
        return view.getCancelButton();
    }

    @Override
    public Button getDeleteButton() {
        return view.getDeleteButton();
    }

    @NotNull
    @Override
    public DeletePelangganView getView() {
        return view;
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
