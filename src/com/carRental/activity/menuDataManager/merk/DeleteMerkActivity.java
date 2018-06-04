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
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextField;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 *
 * @author dika
 */
public final class DeleteMerkActivity extends Activity<DeleteMerkView> implements DeleteMerkView {
    private final DeleteMerkView deleteMerkView = new DeleteMerkViewImpl();
    private Merk merk;

    private void deleteMerk() {
        if (merk != null) {
            showFailed("Tidak ada data merk yang akan dihapus");
            return;
        }

        execute(new MerkServiceImpl(), merkService -> {
            try {
                merkService.destroy(merk);
                Activity<?> parent = getParent();

                if (parent != null && parent instanceof MerkManagerActivity) {
                    ((MerkManagerActivity) parent).refresh();
                }
                stop();
            } catch (Exception e) {
                Logger.INSTANCE.printError(e);
                showFailed("Data merk gagal dihapus");
            }

            return Unit.INSTANCE;
        });
    }

    public void setMerk(Merk merk) {
        SwingUtilities.invokeLater(() -> {
            this.merk = merk;
            getNamaMerkField().setText(merk.getNama());
        });
    }

    @Override
    protected void initListener(DeleteMerkView deleteMerkView) {
        getCancelButton().addActionListener(e -> stop());

        getDeleteButton().addActionListener(e -> deleteMerk());
    }

    @Override
    public TextField getNamaMerkField() {
        return deleteMerkView.getNamaMerkField();
    }

    @Override
    public Button getDeleteButton() {
        return deleteMerkView.getDeleteButton();
    }

    @Override
    public Button getCancelButton() {
        return deleteMerkView.getCancelButton();
    }

    @NotNull
    @Override
    public DeleteMerkView getView() {
        return deleteMerkView;
    }

    @Override
    public Dialog getRoot() {
        return deleteMerkView.getRoot();
    }
}
