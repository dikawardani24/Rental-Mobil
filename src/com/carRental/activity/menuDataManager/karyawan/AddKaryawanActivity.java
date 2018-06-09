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

import com.carRental.activity.menuDataManager.KaryawanManagerActivity;
import com.carRental.model.Karyawan;
import com.carRental.service.KaryawanServiceImpl;
import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.view.component.Button;
import com.dika.view.component.ComboBox;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextArea;
import com.dika.view.component.TextField;

import org.jetbrains.annotations.NotNull;

/**
 * @author dika
 */
public final class AddKaryawanActivity extends InputActivity<AddKaryawanView> implements AddKaryawanView {
    private final AddKaryawanView registerKaryawanView = new AddKaryawanViewImpl();

    private void saveNewKaryawan() {
        if (!validateInput()) {
            return;
        }

        if (!validateNoKtp()) {
            return;
        }

        Karyawan karyawan = new Karyawan();
        karyawan.setNama(getNamaField().getText());
        String jenkel = String.valueOf(getJenkelComboBox().getSelectedItem());
        karyawan.setJenisKelamin(jenkel);
        karyawan.setNoHp(getNoTelpField().getText());
        karyawan.setNoKtp(getNoKtpField().getText());
        karyawan.setAlamat(getAlamatField().getText());

        execute(new KaryawanServiceImpl(), "Data Karyawan Berhasil Disimpan", "Data Karyawan Gagal Disimpan",
                karyawanService -> {
                    karyawanService.create(karyawan);
                    Activity<?> parent = getParent();
                    if (parent instanceof KaryawanManagerActivity) {
                        ((KaryawanManagerActivity) parent).refresh();
                    }
                    clear();
                    return karyawanService;
                });
    }
    
    private boolean validateNoKtp() {
        Karyawan karyawan;

        String noKtp = getNoKtpField().getText();
        try {
            karyawan = execute(new KaryawanServiceImpl(), karyawanService -> karyawanService.findBy(noKtp));
        } catch (Exception e) {
            karyawan = null;
        }

        if (karyawan != null) {
            showFailed("Sudah Ada Data Karyawan Dengan No. Ktp : " + noKtp);
            return false;
        }

        return true;
    }

    @Override
    protected void initListener(AddKaryawanView v) {
        getSaveButton().addActionListener(evt -> saveNewKaryawan());

        getClearButton().addActionListener(evt -> clear());

        getCancelButton().addActionListener(evt -> stop());
    }
    
    @NotNull
    @Override
    public AddKaryawanView getView() {
        return registerKaryawanView;
    }


    @Override
    public TextField getNamaField() {
        return registerKaryawanView.getNamaField();
    }

    @Override
    public ComboBox<String> getJenkelComboBox() {
        return registerKaryawanView.getJenkelComboBox();
    }

    @Override
    public TextField getNoTelpField() {
        return registerKaryawanView.getNoTelpField();
    }

    @Override
    public TextField getNoKtpField() {
        return registerKaryawanView.getNoKtpField();
    }

    @Override
    public TextArea getAlamatField() {
        return registerKaryawanView.getAlamatField();
    }

    @Override
    public Button getClearButton() {
        return registerKaryawanView.getClearButton();
    }

    @Override
    public Button getCancelButton() {
        return registerKaryawanView.getCancelButton();
    }

    @Override
    public Button getSaveButton() {
        return registerKaryawanView.getSaveButton();
    }

    @Override
    public Dialog getRoot() {
        return registerKaryawanView.getRoot();
    }

}
