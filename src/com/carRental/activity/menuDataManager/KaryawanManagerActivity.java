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
import com.carRental.activity.menuDataManager.karyawan.DeleteKaryawanActivity;
import com.carRental.activity.menuDataManager.karyawan.AddKaryawanActivity;
import com.carRental.activity.menuDataManager.karyawan.UpdateKaryawanActivity;
import com.carRental.model.Karyawan;
import com.carRental.report.KaryawanReport;
import com.carRental.service.KaryawanServiceImpl;
import com.carRental.activity.tableModel.KaryawanTableModel;
import com.dika.activity.Activity;
import com.dika.activity.service.OnResumedAction;
import com.dika.activity.service.OnStartedAction;
import com.dika.view.component.Button;
import com.dika.view.component.Frame;
import com.dika.view.component.Table;
import com.dika.view.custom.PagingTableView;
import com.dika.view.custom.PagingTableViewAction;
import com.dika.view.custom.PagingTableViewService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 *
 * @author dika
 */
public final class KaryawanManagerActivity extends Activity<KaryawanManagerView> implements KaryawanManagerView, PagingTableViewService {
    private final KaryawanManagerView view = new KaryawanManagerViewImpl();
    private KaryawanTableModel tableModel;
    private PagingTableViewAction pagingTableViewAction;

    public void refresh() {
        pagingTableViewAction.refreshPage();
    }

    private void printDataKaryawan() {
        if (tableModel.getEntities().isEmpty()) {
            showInfo("Daftar karyawan masih kosong di table");
            return;
        }

        KaryawanReport report = new KaryawanReport();
        report.setKaryawans(tableModel.getEntities());
        showReport(report);
    }

    private Karyawan getKaryawanOnSelectedRow() {
        int selectedRow = getPagingTableView().getTable().getSelectedRow();

        if (selectedRow >= 0) {
            return tableModel.getEntities().get(selectedRow);
        } else {
            return null;
        }
    }

    private void updateKaryawan() {
        Karyawan karyawan = getKaryawanOnSelectedRow();
        if (karyawan != null) {
            UpdateKaryawanActivity activity = startOther(UpdateKaryawanActivity.class);
            activity.setKaryawan(karyawan);
        } else {
            showInfo("Tidak ada baris terpilih");
        }
    }

    private void deleteKaryawan() {
        Karyawan karyawan = getKaryawanOnSelectedRow();
        if (karyawan != null) {
            DeleteKaryawanActivity activity = startOther(DeleteKaryawanActivity.class);
            activity.setKaryawan(karyawan);
        } else {
            showInfo("Tidak ada baris terpilih");
        }
    }

    @NotNull
    @Override
    public KaryawanManagerView getView() {
        return view;
    }

    @Override
    protected void initListener(KaryawanManagerView karyawanManagerView) {
        Table table = getPagingTableView().getTable();

        table.setEditable(false);
        tableModel = new KaryawanTableModel(table);
        pagingTableViewAction = new PagingTableViewAction(this, getPagingTableView(), 50);

        getAddNewKaryawanButton().addActionListener(evt -> startOther(AddKaryawanActivity.class));

        getPrintButton().addActionListener(evt -> printDataKaryawan());
        
        getUpdateKaryawanMenuItem().addActionListener(evt  -> updateKaryawan());

        getDeleteKaryawanMenuItem().addActionListener(evt -> deleteKaryawan());

        add((OnStartedAction) activity -> pagingTableViewAction.toFirstPage());
        add((OnResumedAction) activity -> pagingTableViewAction.refreshPage());
    }

    @Override
    public int countData() {
        return execute(new KaryawanServiceImpl(), KaryawanServiceImpl::count);
    }

    @Override
    public void insertData(int firstResult, int maxResults) {
        execute(new KaryawanServiceImpl(), karyawanService -> {
            List<Karyawan> karyawans = karyawanService.findAll(maxResults, firstResult);
            tableModel.clear();
            tableModel.insert(karyawans);
            return karyawanService;
        });
    }

    @Override
    public Button getAddNewKaryawanButton() {
        return view.getAddNewKaryawanButton();
    }

    @Override
    public Button getPrintButton() {
        return view.getPrintButton();
    }

    @Override
    public PagingTableView getPagingTableView() {
        return view.getPagingTableView();
    }

    @Override
    public WebMenuItem getUpdateKaryawanMenuItem() {
        return view.getUpdateKaryawanMenuItem();
    }

    @Override
    public WebMenuItem getDeleteKaryawanMenuItem() {
        return view.getDeleteKaryawanMenuItem();
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
