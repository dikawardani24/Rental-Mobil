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
package com.carRental.activity.menuDataManager;

import com.alee.laf.menu.WebMenuItem;
import com.carRental.activity.menuDataManager.transaksi.DeleteTransaksiActivity;
import com.carRental.activity.menuDataManager.transaksi.DetailKaryawanActivity;
import com.carRental.activity.menuDataManager.transaksi.DetailPengembalianViewActivity;
import com.carRental.activity.tableModel.TransaksiTableModel;
import com.carRental.model.Karyawan;
import com.carRental.model.Pengembalian;
import com.carRental.model.Sewa;
import com.carRental.report.TransaksiReport;
import com.carRental.service.PengembalianServiceImpl;
import com.carRental.service.SewaServiceImpl;
import com.dika.activity.Activity;
import com.dika.activity.service.OnResumedAction;
import com.dika.activity.service.OnStartedAction;
import com.dika.view.component.Frame;
import com.dika.view.component.Table;
import com.dika.view.component.custom.PrintButton;
import com.dika.view.component.custom.SearchButton;
import com.dika.view.custom.PagingTableViewAction;
import com.dika.view.custom.PagingTableViewImpl;
import com.dika.view.custom.PagingTableViewService;
import datechooser.beans.DateChooserCombo;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

/**
 *
 * @author dika
 */
public final class TransaksiManagerActivity extends Activity<TransaksiManagerView>
        implements TransaksiManagerView, PagingTableViewService {
    private final TransaksiManagerView view = new TransaksiManagerViewImpl();
    private TransaksiTableModel tableModel;
    private PagingTableViewAction action;

    public void refresh() {
        action.refreshPage();
    }

    private Sewa getSewaOnSelectedRow() {
        int selectedRow = getPagingTableViewImpl().getTable().getSelectedRow();
        if (selectedRow >= 0) {
            return tableModel.getEntities().get(selectedRow);
        } else {
            return null;
        }
    }

    private Pengembalian getPengembalianFrom(Sewa sewa) {
        return execute(new PengembalianServiceImpl(), pengembalianService -> {
            try {
                return pengembalianService.findBy(sewa);
            } catch (Exception e) {
                return null;
            }
        });
    }

    private void printDataTransaksi() {
        if (tableModel.getEntities().isEmpty()) {
            showInfo("Tidak Data Transaksi Untuk Dicetak");
            return;
        }

        TransaksiReport report = new TransaksiReport();
        report.setSewaList(tableModel.getEntities());
        showReport(report);
    }

    private void deleteTransaksi() {
        Sewa sewa = getSewaOnSelectedRow();
        if (sewa != null) {
            DeleteTransaksiActivity activity = startOther(DeleteTransaksiActivity.class);
            activity.setSewa(sewa);
        } else {
            showInfo("Tidak Ada Baris Terpilih");
        }
    }

    private void viewDetailPengembalian() {
        Sewa sewa = getSewaOnSelectedRow();

        if (sewa != null) {
            Pengembalian pengembalian = getPengembalianFrom(sewa);

            if (pengembalian != null) {
                DetailPengembalianViewActivity activity = startOther(DetailPengembalianViewActivity.class);
                activity.setPengembalian(pengembalian);
            } else {
                showFailed("Tidak Menemukan Data Pengembalian Untuk Sewa Ini");
            }
        } else {
            showInfo("Tidak Ada Baris Terpilih");
        }
    }

    private void viewDetailPenerima() {
        Sewa sewa = getSewaOnSelectedRow();

        if (sewa != null) {
            Karyawan pemberiSewa = sewa.getPemberiSewa();
            startDetailActivityOf(pemberiSewa);
        } else {
            showInfo("Tidak Ada Baris Terpilih");
        }
    }

    private void viewDetailPenerimaKembali() {
        Sewa sewa = getSewaOnSelectedRow();
        if (sewa != null) {

            Pengembalian pengembalian = getPengembalianFrom(sewa);
            if (pengembalian != null) {
                Karyawan penerimaKembali = pengembalian.getPenerimaKembali();
                startDetailActivityOf(penerimaKembali);
            }
        } else {
            showInfo("Tidak Ada Baris Terpilih");
        }
    }

    private void startDetailActivityOf(Karyawan karyawan) {
        DetailKaryawanActivity activity = startOther(DetailKaryawanActivity.class);
        activity.setKaryawan(karyawan);
    }

    private Date getDateFrom(DateChooserCombo dateChooserCombo) {
        return dateChooserCombo.getSelectedDate().getTime();
    }

    @Override
    public int countData() {
        return execute(new SewaServiceImpl(), sewaService -> {
            Date start = getDateFrom(getStartDateChooer());
            Date end = getDateFrom(getEndDateChooser());
            return sewaService.countBy(start, end);
        });
    }

    @Override
    public void insertData(int firstResult, int maxResults) {
        execute(new SewaServiceImpl(), sewaService -> {
            Date start = getDateFrom(getStartDateChooer());
            Date end = getDateFrom(getEndDateChooser());
            List<Sewa> sewaList = sewaService.findsBy(start, end, firstResult, maxResults);
            tableModel.clear();
            tableModel.insert(sewaList);
            return sewaService;
        });
    }

    @Override
    protected void initListener(TransaksiManagerView transaksiManagerView) {
        Table table = getPagingTableViewImpl().getTable();
        table.setEditable(false);
        tableModel = new TransaksiTableModel(table);
        action = new PagingTableViewAction(this, getPagingTableViewImpl(), 100);

        getSearchButton().addActionListener(e -> action.toFirstPage());

        getPrintButton().addActionListener(e -> printDataTransaksi());

        getPenerimaDetailMenuItem().addActionListener(e -> viewDetailPenerima());

        getPengembalianDetailMenuItem().addActionListener(e -> viewDetailPengembalian());

        getPengembaliDetailMenuItem().addActionListener(e -> viewDetailPenerimaKembali());

        getDeleteMenuItem().addActionListener(e -> deleteTransaksi());

        add((OnStartedAction) activity -> action.toFirstPage());
        add((OnResumedAction) activity -> action.refreshPage());
    }

    @Override
    public WebMenuItem getPengembalianDetailMenuItem() {
        return view.getPengembalianDetailMenuItem();
    }

    @Override
    public WebMenuItem getPengembaliDetailMenuItem() {
        return view.getPengembaliDetailMenuItem();
    }

    @Override
    public WebMenuItem getPenerimaDetailMenuItem() {
        return view.getPenerimaDetailMenuItem();
    }

    @Override
    public WebMenuItem getDeleteMenuItem() {
        return view.getDeleteMenuItem();
    }

    @Override
    public DateChooserCombo getEndDateChooser() {
        return view.getEndDateChooser();
    }

    @Override
    public PagingTableViewImpl getPagingTableViewImpl() {
        return view.getPagingTableViewImpl();
    }

    @Override
    public PrintButton getPrintButton() {
        return view.getPrintButton();
    }

    @Override
    public SearchButton getSearchButton() {
        return view.getSearchButton();
    }

    @Override
    public DateChooserCombo getStartDateChooer() {
        return view.getStartDateChooer();
    }

    @NotNull
    @Override
    public TransaksiManagerView getView() {
        return view;
    }

    @Override
    public Frame getRoot() {
        return view.getRoot();
    }

    @NotNull
    @Override
    public Activity<?> getActivity() {
        return this;
    }
}
