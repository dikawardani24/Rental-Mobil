package com.carRental.report;

import com.carRental.model.Car;
import com.dika.report.DataReport;
import com.dika.util.CollectionHelper;
import com.dika.util.NumberHelper;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.type;

public final class CarReport extends DataReport {
    private List<Car> cars;

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public CarReport() {
        super("Laporan Data Mobil");
    }

    @NotNull
    @Override
    protected DRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("id", "noPlat", "warna", 
                "noRangka", "noMesin", "hargaSewa", "status");
        
        cars.forEach(car -> dataSource.add(
                car.getId(),
                car.getNoPlat(),
                car.getWarna(),
                car.getNoRangka(),
                car.getNoMesin(),
                NumberHelper.INSTANCE.toCurrency(car.getHargaSewa()),
                car.getStatus()
        ));
        return dataSource;
    }

    @NotNull
    @Override
    protected List<TextColumnBuilder<?>> createColumns() {
        TextColumnBuilder<?> idCol = createColumn("ID Mobil", "id", type.integerType());
        TextColumnBuilder<?> noPlatCol = createColumn("No. Plat", "noPlat", type.stringType());
        TextColumnBuilder<?> warnaCol = createColumn("Warna", "warna", type.stringType());
        TextColumnBuilder<?> noRangkaCol = createColumn("No. Rangka", "noRangka", type.stringType());
        TextColumnBuilder<?> noMesinCol = createColumn("No. Mesin", "noMesin", type.stringType());
        TextColumnBuilder<?> hargaSewaCol = createColumn("Harga Sewa", "hargaSewa", type.stringType());
        TextColumnBuilder<?> statusCol = createColumn("Status", "status", type.stringType());

        return CollectionHelper.INSTANCE
                .collectAsArrayList(idCol, noPlatCol, warnaCol, noRangkaCol, 
                        noMesinCol, hargaSewaCol, statusCol);
    }
}
