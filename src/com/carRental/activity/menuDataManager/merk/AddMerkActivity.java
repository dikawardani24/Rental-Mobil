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
package com.carRental.activity.menuDataManager.merk;

import com.carRental.activity.menuDataManager.MerkManagerActivity;
import com.carRental.model.Merk;
import com.carRental.service.MerkServiceImpl;
import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextField;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author dika
 */
public final class AddMerkActivity extends InputActivity<AddMerkView> implements AddMerkView {
    private final AddMerkView addMerkView = new AddMerkViewImpl();

    private void saveMerk() {
        if (!validateInput()) return;

        if (!validateNama()) return;

        Merk merk = new Merk();
        merk.setNama(getNamaMerkField().getText());

        execute(new MerkServiceImpl(), "Data Merk Berhasil Disimpan", "Data Merk Gagal Disimpan", merkService -> {
            merkService.create(merk);
            Activity<?> parent = getParent();
            if (parent instanceof MerkManagerActivity) {
                ((MerkManagerActivity) parent).refresh();
            }
            clear();
            return merkService;
        });
    }

    private boolean validateNama() {
        Merk merk;
        String nama = getNamaMerkField().getText();

        try {
            merk = execute(new MerkServiceImpl(), merkService -> merkService.findBy(nama));
        } catch (Exception e) {
            merk = null;
        }

        if (merk != null) {
            showFailed("Sudah Ada Merk Dengan Nama : "+nama);
            return false;
        }

        return true;
    }

    @Override
    protected void initListener(AddMerkView v) {
        getCancelButton().addActionListener(e -> stop());

        getClearButton().addActionListener(e -> clear());

        getSaveButton().addActionListener(e -> saveMerk());
    }


    @NotNull
    @Override
    public AddMerkView getView() {
        return addMerkView;
    }

    @Override
    public TextField getNamaMerkField() {
        return addMerkView.getNamaMerkField();
    }

    @Override
    public Button getSaveButton() {
        return addMerkView.getSaveButton();
    }

    @Override
    public Button getCancelButton() {
        return addMerkView.getCancelButton();
    }

    @Override
    public Button getClearButton() {
        return addMerkView.getClearButton();
    }

    @Override
    public Dialog getRoot() {
        return addMerkView.getRoot();
    }
}
