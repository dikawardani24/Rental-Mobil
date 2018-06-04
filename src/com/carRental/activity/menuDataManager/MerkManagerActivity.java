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
import com.carRental.activity.menuDataManager.merk.AddMerkActivity;
import com.carRental.activity.menuDataManager.merk.DeleteMerkActivity;
import com.carRental.activity.menuDataManager.merk.UpdateMerkActivity;
import com.carRental.model.Merk;
import com.carRental.service.MerkServiceImpl;
import com.carRental.view.model.MerkTableModel;
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
public final class MerkManagerActivity extends Activity<MerkManagerView> implements MerkManagerView, PagingTableViewService{
    private final MerkManagerView view = new MerkManagerViewImpl();
    private MerkTableModel tableModel;
    private PagingTableViewAction pagingTableViewAction;

    public void refresh() {
        pagingTableViewAction.refreshPage();
    }

    private Merk getMerkOnSelectedRow() {
        int selectedRow = getPagingTableView().getTable().getSelectedRow();

        if (selectedRow >= 0) {
            return tableModel.getEntities().get(selectedRow);
        } else {
            return null;
        }
    }

    private void updateMerk() {
        Merk merk = getMerkOnSelectedRow();

        if (merk != null) {
            UpdateMerkActivity activity = startOther(UpdateMerkActivity.class);
            activity.setMerk(merk);
        } else {
            showInfo("Tidak ada baris terpilih");
        }
    }

    private void deleteMerk() {
        Merk merk = getMerkOnSelectedRow();

        if (merk != null) {
            DeleteMerkActivity activity = startOther(DeleteMerkActivity.class);
            activity.setMerk(merk);
        } else {
            showInfo("Tidak ada baris terpilih");
        }
    }

    @Override
    protected void initListener(MerkManagerView v) {
        tableModel = new MerkTableModel(getPagingTableView().getTable());
        pagingTableViewAction = new PagingTableViewAction(this, getPagingTableView(), 50);

        getAddNewMerkButton().addActionListener(e -> startOther(AddMerkActivity.class));
        getUpdateMerkMenuItem().addActionListener(e -> updateMerk());
        getDeleteMerkMenuItem().addActionListener(e -> deleteMerk());
        add((OnStartedAction) activity -> pagingTableViewAction.toFirstPage());
        add((OnResumedAction) activity -> pagingTableViewAction.refreshPage());
    }

    @Override
    public int countData() {
        return execute(new MerkServiceImpl(), MerkServiceImpl::count);
    }

    @Override
    public void insertData(int firstResult, int maxResults) {
        execute(new MerkServiceImpl(), merkService -> {
            List<Merk> karyawans = merkService.findAll(maxResults, firstResult);
            tableModel.clear();
            tableModel.insert(karyawans);
            return merkService;
        });
    }


    @NotNull
    @Override
    public MerkManagerView getView() {
        return view;
    }

    @Override
    public Button getAddNewMerkButton() {
        return view.getAddNewMerkButton();
    }

    @Override
    public PagingTableView getPagingTableView() {
        return view.getPagingTableView();
    }

    @Override
    public WebMenuItem getUpdateMerkMenuItem() {
        return view.getUpdateMerkMenuItem();
    }

    @Override
    public WebMenuItem getDeleteMerkMenuItem() {
        return view.getDeleteMerkMenuItem();
    }

    @NotNull
    @Override
    public Activity<?> getActivity() {
        return this;
    }

    @Override
    public Frame getRoot() {
        return view.getRoot();
    }
}
