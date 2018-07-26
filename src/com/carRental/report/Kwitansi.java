/*
 * Copyright 2018 dika.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.carRental.report;

import com.carRental.model.Car;
import com.carRental.model.Karyawan;
import com.carRental.model.Sewa;
import com.dika.report.NotaReport;
import com.dika.util.CalendarHelper;
import com.dika.util.NumberHelper;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.constant.PageType;
import org.jetbrains.annotations.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 * @author dika
 */
public class Kwitansi extends NotaReport<Sewa> {

    public Kwitansi(Sewa sewa) {
        super("Kwitansi Sewa", sewa);
    }

    @Override
    public void build() {
        setPageType(PageType.A4);
        super.build();
    }

    @NotNull
    @Override
    protected ComponentBuilder<?, ?> createDetail(Sewa m) {
        HorizontalListBuilder listBuilder = cmp.horizontalList();

        Sewa sewa = getModel();
        Karyawan pemberiSewa = getModel().getPemberiSewa();
        Car car = sewa.getCar();

        addAttribute(listBuilder, "ID Sewa", String.valueOf(sewa.getId()));
        addAttribute(listBuilder, "Tanggal", CalendarHelper.INSTANCE.dateToLocal(sewa.getTglTransaksi()));
        addAttribute(listBuilder, "ID Pemberi Sewa", String.valueOf(pemberiSewa.getId()));
        addAttribute(listBuilder, "Nama Karyawan Penerima", pemberiSewa.getNama());
        addAttribute(listBuilder, "Lama Sewa", String.valueOf(sewa.getLamaSewa()));
        addAttribute(listBuilder, "No. Plat Mobil", car.getNoPlat());
        addAttribute(listBuilder, "Harga Sewa", NumberHelper.INSTANCE.toCurrency(car.getHargaSewa()) + " /hari");
        addAttribute(listBuilder, "Tagihan", NumberHelper.INSTANCE.toCurrency(sewa.getTotalTagihan()));

        return cmp.verticalList(listBuilder);
    }

}
