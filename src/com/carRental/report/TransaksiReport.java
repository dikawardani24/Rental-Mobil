package com.carRental.report;

import com.carRental.model.Car;
import com.carRental.model.Pelanggan;
import com.carRental.model.Pengembalian;
import com.carRental.model.Sewa;
import com.carRental.service.PengembalianServiceImpl;
import com.dika.report.DataReport;
import com.dika.util.CalendarHelper;
import com.dika.util.CollectionHelper;
import com.dika.util.NumberHelper;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.type;

public class TransaksiReport extends DataReport {
    private List<Sewa> sewaList;

    public TransaksiReport() {
        super("Laporan Data Transaksi");
    }

    public void setSewaList(List<Sewa> sewaList) {
        this.sewaList = sewaList;
    }

    @NotNull
    @Override
    protected DRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("id", "noPlat", "warna",
                "noRangka", "noMesin", "hargaSewa", "status");

        sewaList.forEach(sewa -> {
            Pelanggan pelanggan = sewa.getPelanggan();
            Car car = sewa.getCar();
            String tglSewa = CalendarHelper.INSTANCE.dateToLocal(sewa.getTglTransaksi());
            String tagihan = NumberHelper.INSTANCE.toCurrency(sewa.getTotalTagihan());

            Pengembalian pengembalian;
            try {
                pengembalian = new PengembalianServiceImpl().findBy(sewa);
            } catch (Exception e) {
                e.printStackTrace();
                pengembalian = null;
            }

            String status = pengembalian == null ? "Masih Dalam Proses" : "Selesai";

            dataSource.add(
                    sewa.getId(),
                    pelanggan.getNama(),
                    tglSewa,
                    car.getNoPlat(),
                    sewa.getLamaSewa(),
                    tagihan,
                    status
            );
        });

        return dataSource;
    }

    @NotNull
    @Override
    protected List<TextColumnBuilder<?>> createColumns() {
        TextColumnBuilder<?> idCol = createColumn("ID Sewa", "id", type.integerType());
        TextColumnBuilder<?> pelangganCol = createColumn("Pelanggan", "pelanggan", type.stringType());
        TextColumnBuilder<?> tglSewaCol = createColumn("Tgl. Sewa", "tglSewa", type.stringType());
        TextColumnBuilder<?> noPlatCol = createColumn("No. Plat Mobil", "noPlatMobil", type.stringType());
        TextColumnBuilder<?> lamaSewaCol = createColumn("Lama Sewa", "lamaSewa", type.stringType());
        TextColumnBuilder<?> tagihan = createColumn("Tagihan", "tagihan", type.stringType());
        TextColumnBuilder<?> statusCol = createColumn("Status", "status", type.stringType());

        return CollectionHelper.INSTANCE
                .collectAsArrayList(idCol, pelangganCol, tglSewaCol, noPlatCol,
                        lamaSewaCol, tagihan, statusCol);
    }
}
