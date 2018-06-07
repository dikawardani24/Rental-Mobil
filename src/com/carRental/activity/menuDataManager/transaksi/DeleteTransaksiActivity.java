package com.carRental.activity.menuDataManager.transaksi;

import com.carRental.model.Sewa;
import com.carRental.service.SewaServiceImpl;
import com.dika.activity.Activity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.Label;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class DeleteTransaksiActivity extends Activity<DeleteTransaksiView> implements DeleteTransaksiView {
    private final DeleteTransaksiView view = new DeleteTransaksiViewImpl();
    private Sewa sewa;

    public void setSewa(Sewa sewa) {
        SwingUtilities.invokeLater(() -> {
            this.sewa = sewa;
            getIdTransaksiLabel().setText(String.valueOf(sewa.getId()));
        });
    }

    private void delete() {
        if (sewa == null) {
            showInfo("Tidak Ada Data Transaksi Sewa Untuk Dihapus");
            return;
        }

        execute(new SewaServiceImpl(), "Data Transaksi Berhasil Dihapus", "Data Transaksi Gagal Dihapus",
                sewaService -> {
                    sewaService.destroy(sewa);
                    return Unit.INSTANCE;
                });
    }

    @Override
    protected void initListener(DeleteTransaksiView deleteTransaksiView) {
        getConfirmedButton().addActionListener(e -> delete());
        getUnconfirmedButton().addActionListener(e -> stop());
    }

    @NotNull
    @Override
    public DeleteTransaksiView getView() {
        return view;
    }

    @Override
    public Label getIdTransaksiLabel() {
        return view.getIdTransaksiLabel();
    }

    @Override
    public Button getConfirmedButton() {
        return view.getConfirmedButton();
    }

    @Override
    public Button getUnconfirmedButton() {
        return view.getUnconfirmedButton();
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
