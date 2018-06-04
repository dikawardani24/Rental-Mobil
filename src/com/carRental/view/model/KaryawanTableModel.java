package com.carRental.view.model;

import com.alee.laf.table.WebTable;
import com.carRental.model.Karyawan;
import com.dika.view.model.EntityTableModel;

public final class KaryawanTableModel extends EntityTableModel<Karyawan> {

    public KaryawanTableModel(WebTable tableHolder) {
        super(tableHolder);
        initColumns();
    }

    @Override
    protected void initColumns() {
        addColumns("ID Karyawan", "Nama", "Jenis Kelamin", "No. Ktp", "No. HP/Telp");

    }

    @Override
    protected void onInsertNewRow(Karyawan karyawan) {
        addRow(new Object[] {
                karyawan.getId(),
                karyawan.getNama(),
                karyawan.getJenisKelamin(),
                karyawan.getNoKtp(),
                karyawan.getNoHp()
        });
    }

    @Override
    protected void onUpdateRow(Karyawan karyawan) {
        int row = findIndexOf(karyawan);
        setValueAt(karyawan.getId(), row, 0);
        setValueAt(karyawan.getNama(), row, 1);
        setValueAt(karyawan.getJenisKelamin(), row, 2);
        setValueAt(karyawan.getNoKtp(), row, 3);
        setValueAt(karyawan.getNoHp(), row, 4);
    }
}
