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
import com.carRental.model.Car;
import com.dika.util.NumberHelper;
import com.dika.view.model.EntityTableModel;

/**
 *
 * @author dika
 */
public final class ChooseCarTableModel extends EntityTableModel<Car> {

    public ChooseCarTableModel(WebTable tableHolder) {
        super(tableHolder);
        initColumns();
    }

    @Override
    protected void initColumns() {
        addColumns("ID Mobil", "Warna", "Merk", "Harga Sewa", "Status");
    }

    @Override
    protected void onInsertNewRow(Car car) {
        addRow(new Object[] {
                car.getId(),
                car.getWarna(),
                car.getMerk().getNama(),
                NumberHelper.INSTANCE.toCurrency(car.getHargaSewa()),
                car.getStatus()
        });
    }

    @Override
    protected void onUpdateRow(Car car) {
        int row = findIndexOf(car);

        setValueAt(car.getId(), row, 0);
        setValueAt(car.getWarna(), row, 1);
        setValueAt(car.getMerk().getNama(), row, 2);
        setValueAt(NumberHelper.INSTANCE.toCurrency(car.getHargaSewa()), row, 3);
        setValueAt(car.getStatus(), row, 4);
    }
}
