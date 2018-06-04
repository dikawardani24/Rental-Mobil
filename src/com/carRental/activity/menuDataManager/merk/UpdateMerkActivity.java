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
import com.dika.Logger;
import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 *
 * @author dika
 */
public final class UpdateMerkActivity extends InputActivity<UpdateMerkView> implements UpdateMerkView {
    private final UpdateMerkView updateMerkView = new UpdateMerkViewImpl();
    private Merk merk;

    public void setMerk(Merk merk) {
        SwingUtilities.invokeLater(() -> {
            this.merk = merk;
            getNamaMerkField().setText(merk.getNama());
        });
    }

    private void updateMerk() {
        if (!validateInput()) return;

        try {
            Merk old = (Merk) merk.clone();

            merk.setNama(getNamaMerkField().getText());

            if (old.equals(merk)) {
                showInfo("Tidak ada perubahan dilakukan");
                return;
            }

            execute(new MerkServiceImpl(), "Data Merk Berhasil Disimpan", "Data Merk Gagal Disimpan",
                    merkService -> {
                        merkService.update(merk);
                        Activity<?> parent = getParent();
                        if (parent != null && parent instanceof MerkManagerActivity) {
                            ((MerkManagerActivity) parent).refresh();
                        }
                        return merkService;
                    });
        } catch (CloneNotSupportedException e) {
            Logger.INSTANCE.printError(e);
        }
    }

    @Override
    protected void initListener(UpdateMerkView updateMerkView) {
        getCancelButton().addActionListener(e -> stop());
        getClearButton().addActionListener(e -> clear());
        getUpdateButton().addActionListener(e -> updateMerk());
    }

    @Override
    public TextField getNamaMerkField() {
        return updateMerkView.getNamaMerkField();
    }

    @Override
    public Button getUpdateButton() {
        return updateMerkView.getUpdateButton();
    }

    @Override
    public Button getCancelButton() {
        return updateMerkView.getCancelButton();
    }

    @Override
    public Button getClearButton() {
        return updateMerkView.getClearButton();
    }

    @NotNull
    @Override
    public UpdateMerkView getView() {
        return updateMerkView;
    }

    @Override
    public Dialog getRoot() {
        return updateMerkView.getRoot();
    }
}
