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
package com.carRental.activity.main.common;

import com.carRental.model.Pelanggan;
import com.dika.activity.InputActivity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextArea;
import com.dika.view.component.TextField;
import javax.swing.SwingUtilities;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author dika
 */
public class DetailPelangganActivity extends InputActivity<DetailPelangganView>
        implements DetailPelangganView {

    private final DetailPelangganView view = new DetailPelangganViewImpl();

    public void setPelanggan(Pelanggan pelanggan) {
        SwingUtilities.invokeLater(() -> {
            getNamaField().setText(pelanggan.getNama());
            getAlamatField().setText(pelanggan.getAlamat());
            getJenisKelaminField().setText(pelanggan.getJenisKelamin());
            getNoHpField().setText(pelanggan.getNoHp());
            getNoKtpField().setText(pelanggan.getNoKtp());
        });
    }

    @Override
    protected void initListener(DetailPelangganView v) {
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

    @NotNull
    @Override
    public DetailPelangganView getView() {
        return view;
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
