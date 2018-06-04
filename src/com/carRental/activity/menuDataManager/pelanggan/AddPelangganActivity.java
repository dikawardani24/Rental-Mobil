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
package com.carRental.activity.menuDataManager.pelanggan;

import com.carRental.activity.menuDataManager.PelangganManagerActivity;
import com.carRental.model.Pelanggan;
import com.carRental.service.PelangganServiceImpl;
import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.view.component.*;
import org.jetbrains.annotations.NotNull;

/**
 * @author dika
 */
public final class AddPelangganActivity extends InputActivity<AddPelangganView> implements AddPelangganView {
    private final AddPelangganView view = new AddPelangganViewImpl();

    private void saveNewPelanggan() {
        if (!validateInput()) return;

        if (!validateNoKtp()) return;

        Pelanggan pelanggan = new Pelanggan();
        pelanggan.setAlamat(getAlamatField().getText());
        pelanggan.setJenisKelamin(String.valueOf(getJenisKelaminComboBox().getSelectedItem()));
        pelanggan.setNama(getNamaField().getText());
        pelanggan.setNoHp(getNoHpField().getText());
        pelanggan.setNoKtp(getNoKtpField().getText());

        execute(new PelangganServiceImpl(), "Data Pelanggan Berhasil Disimpan",
                "Data Pelanggan Gagal Disimpan", service -> {
                    service.create(pelanggan);
                    Activity<?> parent = getParent();
                    if (parent instanceof PelangganManagerActivity) {
                        ((PelangganManagerActivity) parent).refresh();
                    }
                    return service;
                });
    }

    private boolean validateNoKtp() {
        Pelanggan pelanggan;
        String noKtp = getNoKtpField().getText();

        try {
            pelanggan = execute(new PelangganServiceImpl(), pelangganService -> pelangganService.findBy(noKtp));
        } catch (Exception e) {
            pelanggan = null;
        }

        if (pelanggan != null) {
            showFailed("Sudah Ada Data Pelanggan Dengan No. Ktp : "+noKtp);
            return false;
        }

        return true;
    }

    @Override
    protected void initListener(AddPelangganView addPelangganView) {
        getSaveButton().addActionListener(e -> saveNewPelanggan());

        getClearButton().addActionListener(e -> clear());

        getCancelButton().addActionListener(e -> stop());
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
    public AddPelangganView getView() {
        return view;
    }


    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
