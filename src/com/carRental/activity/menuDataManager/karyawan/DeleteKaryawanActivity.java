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
package com.carRental.activity.menuDataManager.karyawan;

import com.carRental.Session;
import com.carRental.activity.menuDataManager.KaryawanManagerActivity;
import com.carRental.model.Karyawan;
import com.carRental.service.KaryawanServiceImpl;
import com.dika.Logger;
import com.dika.activity.Activity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextArea;
import com.dika.view.component.TextField;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author dika
 */
public final class DeleteKaryawanActivity extends Activity<DeleteKaryawanView> implements DeleteKaryawanView {
    private final DeleteKaryawanView view = new DeleteKaryawanViewImpl();
    private Karyawan karyawan;

    public void setKaryawan(Karyawan karyawan) {
        SwingUtilities.invokeLater(() -> {
            this.karyawan = karyawan;
            getIdKaryawanField().setText(String.valueOf(karyawan.getId()));
            getNamaField().setText(karyawan.getNama());
            getJenkelField().setText(karyawan.getJenisKelamin());
            getNoTelpField().setText(karyawan.getNoHp());
            getNoKtpField().setText(karyawan.getNoKtp());
            getAlamatField().setText(karyawan.getAlamat());
        });
    }
    
    private boolean grantedToDelete() {
        if (karyawan == null) {
            showInfo("Tidak ada data karyawan yang akan dihapus");
            return false;
        }
        
        if (karyawan.equals(Session.getInstance().getKaryawan())) {
            showInfo("Karyawan Sedang Login, Menghapus Data Karyawan Tidak Diijinkan");
            return false;
        }
        
        return true;
    }

    private void deleteKaryawan() {
        if (!grantedToDelete()) {
            return;
        }

        execute(new KaryawanServiceImpl(), karyawanService -> {
            try {
                karyawanService.destroy(karyawan);
                Activity<?> parent = getParent();
                if (parent != null && parent instanceof KaryawanManagerActivity) {
                    ((KaryawanManagerActivity) parent).refresh();
                }
                showSucceed("Data Karyawan Berhasil Dihapus");
                stop();
            } catch (Exception e) {
                Logger.INSTANCE.printError(e);
                showFailed("Data Karyawan Gagal Dihapus");
            }
            return Unit.INSTANCE;
        });
    }

    @Override
    protected void initListener(DeleteKaryawanView v) {
        getCancelButton().addActionListener(evt -> stop());
        getDeleteButton().addActionListener(evt -> deleteKaryawan());
    }


    @NotNull
    @Override
    public DeleteKaryawanView getView() {
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
    public Button getDeleteButton() {
        return view.getDeleteButton();
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
