package com.carRental.report;

import com.carRental.model.Pelanggan;
import com.dika.report.DataReport;
import com.dika.util.CollectionHelper;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.type;

public final class PelangganReport extends DataReport {
    private List<Pelanggan> pelanggans;

    public void setPelanggans(List<Pelanggan> pelanggans) {
        this.pelanggans = pelanggans;
    }

    public PelangganReport() {
        super("Laporan Data Pelanggan");
    }

    @NotNull
    @Override
    protected DRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("id", "nama", "jenkel", "noTelp", "noKtp", "alamat");

        pelanggans.forEach(pelanggan -> dataSource.add(
                pelanggan.getId(),
                pelanggan.getNama(),
                pelanggan.getJenisKelamin(),
                pelanggan.getNoHp(),
                pelanggan.getNoKtp(),
                pelanggan.getAlamat()
        ));

        return dataSource;
    }

    @NotNull
    @Override
    protected List<TextColumnBuilder<?>> createColumns() {
        TextColumnBuilder<?> idCol = createColumn("ID Pelanggan", "id", type.integerType());
        TextColumnBuilder<?> namaCol = createColumn("Nama", "nama", type.stringType());
        TextColumnBuilder<?> jenkelCol = createColumn("Jenis Kelamin", "jenkel", type.stringType());
        TextColumnBuilder<?> noTelpCol = createColumn("No. Telp", "noTelp", type.stringType());
        TextColumnBuilder<?> noKtpCol = createColumn("No. KTP", "noKtp", type.stringType());
        TextColumnBuilder<?> alamatCol = createColumn("Alamat", "alamat", type.stringType());

        return CollectionHelper.INSTANCE
                .collectAsArrayList(idCol, namaCol, jenkelCol, noTelpCol, noKtpCol, alamatCol);
    }
}
