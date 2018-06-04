package com.carRental.report;

import com.carRental.model.Karyawan;
import com.dika.report.DataReport;
import com.dika.util.CollectionHelper;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.type;

public final class KaryawanReport extends DataReport {
    private List<Karyawan> karyawans = Collections.emptyList();

    public void setKaryawans(List<Karyawan> karyawans) {
        this.karyawans = karyawans;
    }

    public KaryawanReport() {
        super("Laporan Data Karyawan");
    }

    @NotNull
    @Override
    protected DRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("id", "nama", "jenkel", "noTelp", "noKtp", "alamat");

        karyawans.forEach(karyawan -> dataSource.add(
                karyawan.getId(),
                karyawan.getNama(),
                karyawan.getJenisKelamin(),
                karyawan.getNoHp(),
                karyawan.getNoKtp(),
                karyawan.getAlamat()
        ));

        return dataSource;
    }

    @NotNull
    @Override
    protected List<TextColumnBuilder<?>> createColumns() {
        TextColumnBuilder<?> idCol = createColumn("ID", "id", type.integerType());
        TextColumnBuilder<?> namaCol = createColumn("Nama", "nama", type.stringType());
        TextColumnBuilder<?> jenkelCol = createColumn("Jenis Kelamin", "jenkel", type.stringType());
        TextColumnBuilder<?> noTelpCol = createColumn("No. Telp", "noTelp", type.stringType());
        TextColumnBuilder<?> noKtpCol = createColumn("No. KTP", "noKtp", type.stringType());
        TextColumnBuilder<?> alamatCol = createColumn("Alamat", "alamat", type.stringType());
        
        return CollectionHelper.INSTANCE
                .collectAsArrayList(idCol, namaCol, jenkelCol, noTelpCol, noKtpCol, alamatCol);
    }
}
