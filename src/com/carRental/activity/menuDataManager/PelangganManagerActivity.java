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
import com.carRental.activity.menuDataManager.pelanggan.AddPelangganActivity;
import com.carRental.activity.menuDataManager.pelanggan.DeletePelangganActivity;
import com.carRental.activity.menuDataManager.pelanggan.UpdatePelangganActivity;
import com.carRental.model.Pelanggan;
import com.carRental.report.PelangganReport;
import com.carRental.service.PelangganServiceImpl;
import com.carRental.view.model.PelangganTableModel;
import com.dika.activity.Activity;
import com.dika.activity.service.OnResumedAction;
import com.dika.activity.service.OnStartedAction;
import com.dika.view.component.Button;
import com.dika.view.component.Frame;
import com.dika.view.custom.PagingTableView;
import com.dika.view.custom.PagingTableViewAction;
import com.dika.view.custom.PagingTableViewService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 *
 * @author dika
 */
public final class PelangganManagerActivity extends Activity<PelangganManagerView> implements PelangganManagerView, PagingTableViewService {
    private final PelangganManagerView view = new PelangganManagerViewImpl();
    private PelangganTableModel tableModel;
    private PagingTableViewAction pagingTableViewAction;

    public void refresh() {
        pagingTableViewAction.refreshPage();
    }

    private Pelanggan getPelangganOnSelectedRow() {
        int selectedRow = getPagingTableView().getTable().getSelectedRow();

        if (selectedRow >= 0) {
            return tableModel.getEntities().get(selectedRow);
        } else {
            return null;
        }
    }

    private void printDataPelanggan() {
        if (tableModel.getEntities().isEmpty()) {
            showInfo("Daftar karyawan masih kosong di table");
            return;
        }

        PelangganReport report = new PelangganReport();
        report.setPelanggans(tableModel.getEntities());
        showReport(report);
    }

    private void updatePelanggan() {
        Pelanggan pelanggan = getPelangganOnSelectedRow();

        if (pelanggan != null) {
            UpdatePelangganActivity activity = startOther(UpdatePelangganActivity.class);
            activity.setPelanggan(pelanggan);
        } else {
            showInfo("Tidak ada baris terpilih");
        }
    }

    private void deletePelanggan() {
        Pelanggan pelanggan = getPelangganOnSelectedRow();
        if (pelanggan != null) {
            DeletePelangganActivity activity = startOther(DeletePelangganActivity.class);
            activity.setPelanggan(pelanggan);
        } else {
            showInfo("Tidak ada baris terpilih");
        }
    }

    @Override
    protected void initListener(PelangganManagerView v) {
        tableModel = new PelangganTableModel(getPagingTableView().getTable());
        pagingTableViewAction = new PagingTableViewAction(this, getPagingTableView(), 50);

        getAddNewPelangganButton().addActionListener(evt -> startOther(AddPelangganActivity.class));

        getPrintButton().addActionListener(evt -> printDataPelanggan());

        getUpdatePelangganMenuItem().addActionListener(evt  -> updatePelanggan());

        getDeletePelangganMenuItem().addActionListener(evt -> deletePelanggan());

        add((OnStartedAction) activity -> pagingTableViewAction.toFirstPage());
        add((OnResumedAction) activity -> pagingTableViewAction.refreshPage());
    }

    @Override
    public int countData() {
        return execute(new PelangganServiceImpl(), PelangganServiceImpl::count);
    }

    @Override
    public void insertData(int firstResult, int maxResults) {
        execute(new PelangganServiceImpl(), service -> {
            List<Pelanggan> pelanggans = service.findAll(maxResults, firstResult);
            tableModel.clear();
            tableModel.insert(pelanggans);
            return service;
        });
    }

    @NotNull
    @Override
    public PelangganManagerView getView() {
        return view;
    }

    @Override
    public Button getAddNewPelangganButton() {
        return view.getAddNewPelangganButton();
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
    public WebMenuItem getUpdatePelangganMenuItem() {
        return view.getUpdatePelangganMenuItem();
    }

    @Override
    public WebMenuItem getDeletePelangganMenuItem() {
        return view.getDeletePelangganMenuItem();
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
