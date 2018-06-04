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
package com.carRental.activity.main.sewa;

import com.carRental.activity.main.sewa.InputCarContainer;
import com.carRental.activity.main.sewa.InputPelangganContainer;
import com.carRental.activity.main.sewa.TagihanContainer;
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
public interface SewaContainer extends InputContainer<Panel>, InputCarContainer,
        InputPelangganContainer, TagihanContainer {
    InputCarContainer getInputCarContainer();

    InputPelangganContainer getInputPelangganContainer();

    TagihanContainer getTagihanContainer();

    PagingTableView getPagingTableView();

    Button getSaveButton();

    Button getClearButton();

    @Override
    default TextField getNoPlatField() {
        return getInputCarContainer().getNoPlatField();
    }

    @Override
    default CurrencyFormattedTextField getHargaSewaField() {
        return getInputCarContainer().getHargaSewaField();
    }

    @Override
    default Button getChooseCarButton() {
        return getInputCarContainer().getChooseCarButton();
    }

    @Override
    default Button getSeeDetailCarButton() {
        return getInputCarContainer().getSeeDetailCarButton();
    }

    @Override
    default TextField getNamaPelangganField() {
        return getInputPelangganContainer().getNamaPelangganField();
    }

    @Override
    default TextField getNoKtpField() {
        return getInputPelangganContainer().getNoKtpField();
    }

    @Override
    public default TextField getNoTelpField() {
        return getInputPelangganContainer().getNoTelpField();
    }

    @Override
    default Button getSearchlPelangganButton() {
        return getInputPelangganContainer().getSearchlPelangganButton();
    }

    @Override
    default DecimalFormattedTextField getLamaSewaField() {
        return getTagihanContainer().getLamaSewaField();
    }

    @Override
    default CurrencyFormattedTextField getTagihanField() {
        return getTagihanContainer().getTagihanField();
    }

    @Override
    default CurrencyFormattedTextField getPaidField() {
        return getTagihanContainer().getPaidField();
    }

    @Override
    default CurrencyFormattedTextField getKembalianField() {
        return getTagihanContainer().getKembalianField();
    }

    @Override
    default Button getCalculatorButton() {
        return getTagihanContainer().getCalculatorButton();
    }

    @Override
    default List<JTextComponent> getTextComponents() {
        List<JTextComponent> textComponents = new ArrayList<>();

        textComponents.addAll(getInputCarContainer().getTextComponents());
        textComponents.addAll(getInputPelangganContainer().getTextComponents());
        textComponents.addAll(getTagihanContainer().getTextComponents());

        return textComponents;
    }
}
