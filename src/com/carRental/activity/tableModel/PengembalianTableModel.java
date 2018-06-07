package com.carRental.activity.tableModel;

import com.alee.laf.table.WebTable;
import com.carRental.model.Pengembalian;
import com.carRental.model.Sewa;
import com.dika.util.CalendarHelper;
import com.dika.util.NumberHelper;
import com.dika.view.model.EntityTableModel;

public final class PengembalianTableModel extends EntityTableModel<Pengembalian> {
    public PengembalianTableModel(WebTable tableHolder) {
        super(tableHolder);
        initColumns();
    }

    @Override
    protected void initColumns() {
        addColumns("ID Pengembalian", "No. Plat", "Pelanggan", "Penerima",
                "Tgl Kembali", "Lama Sewa", "Tagihan");
    }

    @Override
    protected void onInsertNewRow(Pengembalian pengembalian) {
        Sewa sewa = pengembalian.getSewa();

        addRow(new Object[] {
                pengembalian.getId(),
                sewa.getCar().getNoPlat(),
                sewa.getPelanggan().getNama(),
                pengembalian.getPenerimaKembali().getNama(),
                CalendarHelper.INSTANCE.dateToLocal(pengembalian.getTglTransaksi()),
                sewa.getLamaSewa(),
                NumberHelper.INSTANCE.toCurrency(sewa.getTotalTagihan())
        });
    }

    @Override
    protected void onUpdateRow(Pengembalian pengembalian) {
        int row = findIndexOf(pengembalian);
        Sewa sewa = pengembalian.getSewa();

        setValueAt(pengembalian.getId(), row, 0);
        setValueAt(sewa.getCar().getNoPlat(), row, 1);
        setValueAt(sewa.getPelanggan().getNama(), row, 2);
        setValueAt(pengembalian.getPenerimaKembali().getNama(), row, 3);
        setValueAt(CalendarHelper.INSTANCE.dateToLocal(pengembalian.getTglTransaksi()), row, 4);
        setValueAt(sewa.getLamaSewa(), row, 5);
        setValueAt(NumberHelper.INSTANCE.toCurrency(sewa.getTotalTagihan()), row, 6);
    }
}
