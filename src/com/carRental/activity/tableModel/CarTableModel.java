package com.carRental.activity.tableModel;

import com.alee.laf.table.WebTable;
import com.carRental.model.Car;
import com.dika.util.NumberHelper;
import com.dika.view.model.EntityTableModel;

public final class CarTableModel extends EntityTableModel<Car> {
    public CarTableModel(WebTable tableHolder) {
        super(tableHolder);
        initColumns();
    }

    @Override
    protected void initColumns() {
        addColumns("ID Mobil", "No. Plat", "Warna", "No. Rangka", "No. Mesin", "Merk", "Harga Sewa", "Status");
    }

    @Override
    protected void onInsertNewRow(Car car) {
        addRow(new Object[] {
                car.getId(),
                car.getNoPlat(),
                car.getWarna(),
                car.getNoRangka(),
                car.getNoMesin(),
                car.getMerk().getNama(),
                NumberHelper.INSTANCE.toCurrency(car.getHargaSewa()),
                car.getStatus()
        });
    }

    @Override
    protected void onUpdateRow(Car car) {
        int row = findIndexOf(car);

        setValueAt(car.getId(), row, 0);
        setValueAt(car.getNoPlat(), row, 1);
        setValueAt(car.getWarna(), row, 2);
        setValueAt(car.getNoRangka(), row, 3);
        setValueAt(car.getNoMesin(), row, 4);
        setValueAt(car.getMerk().getNama(), row, 5);
        setValueAt(NumberHelper.INSTANCE.toCurrency(car.getHargaSewa()), row, 6);
        setValueAt(car.getStatus(), row, 7);
    }
}
