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

import com.dika.util.CollectionHelper;
import com.dika.view.InputContainer;
import com.dika.view.component.Button;
import com.dika.view.component.Panel;
import com.dika.view.component.TextField;
import com.dika.view.component.custom.CurrencyFormattedTextField;
import java.util.List;
import javax.swing.text.JTextComponent;

/**
 *
 * @author dika
 */
public interface DetailCarContainer extends InputContainer<Panel> {

    TextField getNoPlatField();

    CurrencyFormattedTextField getHargaSewaField();

    Button getSeeDetailCarButton();

    @Override
    default List<JTextComponent> getTextComponents() {
        return CollectionHelper.INSTANCE.collectAsArrayList(
                getNoPlatField(),
                getHargaSewaField()
        );
    }

}
