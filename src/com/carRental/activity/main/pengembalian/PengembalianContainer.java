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
package com.carRental.activity.main.pengembalian;

import com.carRental.activity.main.pengembalian.DetailCarContainer;
import com.carRental.activity.main.pengembalian.DetailPelangganContainer;
import com.carRental.activity.main.pengembalian.DetailTagihanContainer;
import com.dika.view.InputContainer;
import com.dika.view.component.Button;
import com.dika.view.component.Panel;
import com.dika.view.component.TextField;
import com.dika.view.component.custom.CurrencyFormattedTextField;
import com.dika.view.component.custom.DecimalFormattedTextField;
import com.dika.view.custom.PagingTableView;

import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dika
 */
public interface PengembalianContainer extends InputContainer<Panel>, DetailCarContainer,
        DetailPelangganContainer, DetailTagihanContainer {
    TextField getIdSewaField();

    DetailCarContainer getDetailCarContainer();

    DetailPelangganContainer getDetailPelangganContainer();

    DetailTagihanContainer getDetailTagihanContainer();

    PagingTableView getPagingTableView();

    Button getSearchButton();

    Button getSaveButton();

    Button getClearButton();

    @Override
    default TextField getNoPlatField() {
        return getDetailCarContainer().getNoPlatField();
    }

    @Override
    default CurrencyFormattedTextField getHargaSewaField() {
        return getDetailCarContainer().getHargaSewaField();
    }

    @Override
    default Button getSeeDetailCarButton() {
        return getDetailCarContainer().getSeeDetailCarButton();
    }

    @Override
    default TextField getNamaPelangganField() {
        return getDetailPelangganContainer().getNamaPelangganField();
    }

    @Override
    default TextField getNoHpField() {
        return getDetailPelangganContainer().getNoHpField();
    }

    @Override
    default Button getSeeDetailPelanggan() {
        return getDetailPelangganContainer().getSeeDetailPelanggan();
    }

    @Override
    default DecimalFormattedTextField getLamaSewaField() {
        return getDetailTagihanContainer().getLamaSewaField();
    }

    @Override
    default DecimalFormattedTextField getOvertimeField() {
        return getDetailTagihanContainer().getOvertimeField();
    }

    @Override
    default CurrencyFormattedTextField getTagihanField() {
        return getDetailTagihanContainer().getTagihanField();
    }

    @Override
    default CurrencyFormattedTextField getPaidField() {
        return getDetailTagihanContainer().getPaidField();
    }

    @Override
    default CurrencyFormattedTextField getKembalianField() {
        return getDetailTagihanContainer().getKembalianField();
    }

    @Override
    default Button getCalculateButton() {
        return getDetailTagihanContainer().getCalculateButton();
    }

    @Override
    default List<JTextComponent> getTextComponents() {
        List<JTextComponent> textComponents = new ArrayList<>();

        textComponents.addAll(getDetailCarContainer().getTextComponents());
        textComponents.addAll(getDetailPelangganContainer().getTextComponents());
        textComponents.addAll(getDetailTagihanContainer().getTextComponents());

        return textComponents;
    }
}
