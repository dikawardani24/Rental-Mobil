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
import com.carRental.activity.menuDataManager.user.AddUserActivity;
import com.carRental.activity.menuDataManager.user.DeleteUserActivity;
import com.carRental.model.User;
import com.carRental.report.UserReport;
import com.carRental.service.UserServiceImpl;
import com.carRental.view.model.UserTableModel;
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
public final class UserManagerActivity extends Activity<UserManagerView> implements UserManagerView, PagingTableViewService {
    private final UserManagerView view = new UserManagerViewImpl();
    private UserTableModel tableModel;
    private PagingTableViewAction pagingTableViewAction;

    public void refresh() {
        pagingTableViewAction.refreshPage();
    }

    private void printDataUser() {
        UserReport report = new UserReport();
        report.setUsers(tableModel.getEntities());
        showReport(report);
    }

    private void deleteUser() {
        int selectedRow = getPagingTableView().getTable().getSelectedRow();

        if (selectedRow >= 0) {
            User user = tableModel.getEntities().get(selectedRow);
            DeleteUserActivity activity = startOther(DeleteUserActivity.class);
            activity.setUser(user);
        }
    }

    @Override
    protected void initListener(UserManagerView userManagerView) {
        tableModel = new UserTableModel(getPagingTableView().getTable());
        pagingTableViewAction = new PagingTableViewAction(this, getPagingTableView(), 50);

        getAddNewKaryawanButton().addActionListener(evt -> startOther(AddUserActivity.class));
        getPrintButton().addActionListener(evt -> printDataUser());
        getDeletePaketMenuItem().addActionListener(evt -> deleteUser());

        add((OnStartedAction) activity -> pagingTableViewAction.toFirstPage());
        add((OnResumedAction) activity -> pagingTableViewAction.refreshPage());
    }
    
    @Override
    public int countData() {
        return execute(new UserServiceImpl(), UserServiceImpl::count);
    }

    @Override
    public void insertData(int firstResult, int maxResults) {
        execute(new UserServiceImpl(), userService -> {
            List<User> users = userService.findAll(maxResults, firstResult);
            tableModel.clear();
            tableModel.insert(users);
            return userService;
        });
    }

    @NotNull
    @Override
    public UserManagerView getView() {
        return view;
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
    public WebMenuItem getDeletePaketMenuItem() {
        return view.getDeletePaketMenuItem();
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
