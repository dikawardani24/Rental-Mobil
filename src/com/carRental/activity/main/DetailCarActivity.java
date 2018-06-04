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
package com.carRental.activity.main;

import com.carRental.model.Car;
import com.dika.activity.InputActivity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextField;
import com.dika.view.component.custom.CurrencyFormattedTextField;
import javax.swing.SwingUtilities;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author dika
 */
public class DetailCarActivity extends InputActivity<DetailCarView> implements DetailCarView {

    private final DetailCarView view = new DetailCarViewImpl();

    public void setCar(Car car) {
        SwingUtilities.invokeLater(() -> {
            getIdCarField().setText(String.valueOf(car.getId()));
            getNoPlatField().setText(car.getNoPlat());
            getWarnaField().setText(car.getWarna());
            getNoRangkaField().setText(car.getNoRangka());
            getNoMesinField().setText(car.getNoMesin());
            getHargaSewaField().setValue(car.getHargaSewa());
            getMerkField().setText(car.getMerk().getNama());
        });
    }

    @Override
    protected void initListener(DetailCarView v) {
        getCancelButton().addActionListener(evt -> stop());
    }

    @NotNull
    @Override
    public DetailCarView getView() {
        return view;
    }

    @Override
    public TextField getIdCarField() {
        return view.getIdCarField();
    }

    @Override
    public TextField getNoPlatField() {
        return view.getNoPlatField();
    }

    @Override
    public TextField getWarnaField() {
        return view.getWarnaField();
    }

    @Override
    public TextField getNoRangkaField() {
        return view.getNoRangkaField();
    }

    @Override
    public TextField getNoMesinField() {
        return view.getNoMesinField();
    }

    @Override
    public CurrencyFormattedTextField getHargaSewaField() {
        return view.getHargaSewaField();
    }

    @Override
    public TextField getMerkField() {
        return view.getMerkField();
    }

    @Override
    public Button getCancelButton() {
        return view.getCancelButton();
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }

}
