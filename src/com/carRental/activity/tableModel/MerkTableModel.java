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
package com.carRental.activity.tableModel;

import com.alee.laf.table.WebTable;
import com.carRental.model.Merk;
import com.dika.view.model.EntityTableModel;

/**
 *
 * @author dika
 */
public final class MerkTableModel extends EntityTableModel<Merk> {

    public MerkTableModel(WebTable tableHolder) {
        super(tableHolder);
        initColumns();
    }

    @Override
    protected void initColumns() {
        addColumns("ID Merk", "Nama");
    }

    @Override
    protected void onInsertNewRow(Merk merk) {
        addRow(new Object[] {
                merk.getId(),
                merk.getNama()
        });
    }

    @Override
    protected void onUpdateRow(Merk merk) {
        int row = findIndexOf(merk);

        setValueAt(merk.getId(), row, 0);
        setValueAt(merk.getNama(), row, 1);
    }
}
