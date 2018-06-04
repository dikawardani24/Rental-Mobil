package com.carRental.view.model;

import com.alee.laf.table.WebTable;
import com.carRental.model.Pelanggan;
import com.dika.view.model.EntityTableModel;

public final class PelangganTableModel extends EntityTableModel<Pelanggan>{
    public PelangganTableModel(WebTable tableHolder) {
        super(tableHolder);
        initColumns();
    }

    @Override
    protected void initColumns() {
        addColumns("ID Pelanggan", "Nama", "No. Ktp", "No. Hp", "Alamat");
    }

    @Override
    protected void onInsertNewRow(Pelanggan pelanggan) {
        addRow(new Object[] {
                pelanggan.getId(),
                pelanggan.getNama(),
                pelanggan.getNoKtp(),
                pelanggan.getNoHp(),
                pelanggan.getAlamat()
        });
    }

    @Override
    protected void onUpdateRow(Pelanggan pelanggan) {
        int row = findIndexOf(pelanggan);

        setValueAt(pelanggan.getId(), row, 0);
        setValueAt(pelanggan.getNama(), row, 1);
        setValueAt(pelanggan.getNoKtp(), row, 2);
        setValueAt(pelanggan.getNoHp(), row, 3);
        setValueAt(pelanggan.getAlamat(), row, 4);
    }
}
