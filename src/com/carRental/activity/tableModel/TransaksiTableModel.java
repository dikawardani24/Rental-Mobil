package com.carRental.activity.tableModel;

import com.alee.laf.table.WebTable;
import com.carRental.model.Car;
import com.carRental.model.Pelanggan;
import com.carRental.model.Pengembalian;
import com.carRental.model.Sewa;
import com.carRental.service.PengembalianServiceImpl;
import com.dika.util.CalendarHelper;
import com.dika.util.NumberHelper;
import com.dika.view.model.EntityTableModel;

public final class TransaksiTableModel extends EntityTableModel<Sewa> {
    public TransaksiTableModel(WebTable tableHolder) {
        super(tableHolder);
        initColumns();
    }

    @Override
    protected void initColumns() {
        addColumns("ID Sewa", "Pelanggan", "Tgl Sewa", "No. Plat Mobil", "Lama Sewa", "Tagihan", "Status");
    }

    @Override
    protected void onInsertNewRow(Sewa sewa) {
        Pelanggan pelanggan = sewa.getPelanggan();
        String tglSewa = CalendarHelper.INSTANCE.dateToLocal(sewa.getTglTransaksi());
        Car car = sewa.getCar();
        String tagihan = NumberHelper.INSTANCE.toCurrency(sewa.getTotalTagihan());

        Pengembalian pengembalian;
        try {
            pengembalian = new PengembalianServiceImpl().findBy(sewa);
        } catch (Exception e) {
            e.printStackTrace();
            pengembalian = null;
        }

        String status = pengembalian == null ? "Masih Dalam Proses" : "Selesai";

        addRow(new Object[] {
                sewa.getId(),
                pelanggan.getNama(),
                tglSewa,
                car.getNoPlat(),
                sewa.getLamaSewa(),
                tagihan,
                status
        });
    }

    @Override
    protected void onUpdateRow(Sewa sewa) {
        int row = findIndexOf(sewa);

        Pelanggan pelanggan = sewa.getPelanggan();
        String tglSewa = CalendarHelper.INSTANCE.dateToLocal(sewa.getTglTransaksi());
        Car car = sewa.getCar();
        String tagihan = NumberHelper.INSTANCE.toCurrency(sewa.getTotalTagihan());

        Pengembalian pengembalian;
        try {
            pengembalian = new PengembalianServiceImpl().findBy(sewa);
        } catch (Exception e) {
            e.printStackTrace();
            pengembalian = null;
        }

        String status = pengembalian == null ? "Masih Dalam Proses" : "Selesai";

        setValueAt(sewa.getId(), row, 0);
        setValueAt(pelanggan.getNama(), row, 1);
        setValueAt(tglSewa, row, 2);
        setValueAt(car.getNoPlat(), row, 3);
        setValueAt(sewa.getLamaSewa(), row, 4);
        setValueAt(tagihan, row, 5);
        setValueAt(status, row, 6);
    }
}
