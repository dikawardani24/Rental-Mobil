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

import com.dika.util.CollectionHelper;
import com.dika.view.InputView;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextArea;
import com.dika.view.component.TextField;

import javax.swing.text.JTextComponent;
import java.util.List;

/**
 * @author dika
 */
public interface DeletePelangganView extends InputView<Dialog> {
    TextField getNamaField();

    TextArea getAlamatField();

    TextField getJenisKelaminField();

    TextField getNoKtpField();

    TextField getNoHpField();

    Button getCancelButton();
    
    Button getDeleteButton();
    
    @Override
    default List<JTextComponent> getTextComponents() {
        return CollectionHelper.INSTANCE.collectAsArrayList(
                getNamaField(),
                getAlamatField(),
                getJenisKelaminField(),
                getNoKtpField(),
                getNoHpField()
        );
    }
}
