package com.carRental.report;

import com.carRental.model.Karyawan;
import com.carRental.model.User;
import com.dika.report.DataReport;
import com.dika.util.CollectionHelper;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.type;

public final class UserReport extends DataReport {
    private List<User> users = Collections.emptyList();

    public UserReport() {
        super("Laporan Data User");
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @NotNull
    @Override
    protected DRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("id", "username", "idKaryawan", "namaKaryawan");

        users.forEach(user -> {
            Karyawan karyawan = user.getKaryawan();
            dataSource.add(
                    user.getId(),
                    user.getUsername(),
                    karyawan.getId(),
                    karyawan.getNama()
            );
        });
        return dataSource;
    }

    @NotNull
    @Override
    protected List<TextColumnBuilder<?>> createColumns() {
        TextColumnBuilder<?> id = createColumn("ID User", "id", type.integerType());
        TextColumnBuilder<?> username = createColumn("Username", "username", type.stringType());
        TextColumnBuilder<?> idKaryawan = createColumn("ID Karyawan", "idKaryawan", type.integerType());
        TextColumnBuilder<?> nama = createColumn("Nama Karyawan", "namaKaryawan", type.stringType());

        return CollectionHelper.INSTANCE.collectAsArrayList(
                id, username, idKaryawan, nama
        );
    }
}
