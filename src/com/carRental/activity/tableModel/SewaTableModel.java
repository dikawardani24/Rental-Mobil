package com.carRental.activity.tableModel;

import com.alee.laf.table.WebTable;
import com.carRental.model.Car;
import com.carRental.model.Karyawan;
import com.carRental.model.Pelanggan;
import com.carRental.model.Sewa;
import com.dika.util.CalendarHelper;
import com.dika.util.NumberHelper;
import com.dika.view.model.EntityTableModel;

public final class SewaTableModel extends EntityTableModel<Sewa> {
    public SewaTableModel(WebTable tableHolder) {
        super(tableHolder);
        initColumns();
    }

    @Override
    protected void initColumns() {
        addColumns("ID Sewa", "No. Plat", "Pelanggan", "Pemberi Sewa",
                "Tgl Sewa", "Lama Sewa", "Tagihan");
    }

    @Override
    protected void onInsertNewRow(Sewa sewa) {
        String unknown = "Tidak Diketahui";

        Car car = sewa.getCar();
        String noPlat = car != null ? car.getNoPlat() : unknown;

        Pelanggan pelanggan = sewa.getPelanggan();
        String namaPelanggan = pelanggan != null ? pelanggan.getNama(): unknown;

        Karyawan karyawan = sewa.getPemberiSewa();
        String namaPemeberiSewa = karyawan != null ? karyawan.getNama(): unknown;


        addRow(new Object[] {
                sewa.getId(),
                noPlat,
                namaPelanggan,
                namaPemeberiSewa,
                CalendarHelper.INSTANCE.dateToLocal(sewa.getTglTransaksi()),
                sewa.getLamaSewa(),
                NumberHelper.INSTANCE.toCurrency(sewa.getTotalTagihan())
        });
    }

    @Override
    protected void onUpdateRow(Sewa sewa) {
        int row = findIndexOf(sewa);

        setValueAt(sewa.getId(), row, 0);
        setValueAt(sewa.getCar().getNoPlat(), row, 1);
        setValueAt(sewa.getPelanggan().getNama(), row, 2);
        setValueAt(sewa.getPemberiSewa().getNama(), row, 3);
        setValueAt(CalendarHelper.INSTANCE.dateToLocal(sewa.getTglTransaksi()), row, 4);
        setValueAt(sewa.getLamaSewa(), row, 5);
        setValueAt(NumberHelper.INSTANCE.toCurrency(sewa.getTotalTagihan()), row, 6);
    }
}
