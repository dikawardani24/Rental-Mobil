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
package com.carRental.activity.menuProgram;

import com.carRental.Session;
import com.carRental.model.User;
import com.carRental.service.UserServiceImpl;
import com.dika.activity.InputActivity;
import com.dika.activity.service.OnResumedAction;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextField;
import org.jetbrains.annotations.NotNull;

/**
 * @author dika
 */
public final class ChangeUsernameActivity extends InputActivity<ChangeUsernameView> implements ChangeUsernameView {
    private final ChangeUsernameView changeUsernameView = new ChangeUsernameViewImpl();
    private final User user = Session.getInstance().getUser();

    private void updateUsername() {
        if (!validateInput()) return;

        String oldUsername = user.getUsername();
        execute(new UserServiceImpl(), userService -> {
            try {
                user.setUsername(getUsernameNewField().getText());
                userService.update(user);
                showFailed("Username berhasil diperbaharui");
            } catch (Exception e) {
                user.setUsername(oldUsername);
                showFailed("Username berhasil diperbaharui");
            }

            return userService;
        });
    }

    @Override
    public void clear() {
        getUsernameNewField().clear();
    }

    @NotNull
    @Override
    public ChangeUsernameView getView() {
        return changeUsernameView;
    }

    @Override
    protected void initListener(ChangeUsernameView changeUsernameView) {
        getIdKaryawanField().setText(String.valueOf(user.getKaryawan().getId()));
        getUsernameOldField().setText(user.getUsername());

        getClearButton().addActionListener(evt -> clear());
        getCancelButton().addActionListener(evt -> stop());
        getSaveButton().addActionListener(evt -> updateUsername());

        add((OnResumedAction) activity -> clear());
    }

    @Override
    public TextField getIdKaryawanField() {
        return changeUsernameView.getIdKaryawanField();
    }

    @Override
    public TextField getUsernameOldField() {
        return changeUsernameView.getUsernameOldField();
    }

    @Override
    public TextField getUsernameNewField() {
        return changeUsernameView.getUsernameNewField();
    }

    @Override
    public Button getSaveButton() {
        return changeUsernameView.getSaveButton();
    }

    @Override
    public Button getCancelButton() {
        return changeUsernameView.getCancelButton();
    }

    @Override
    public Button getClearButton() {
        return changeUsernameView.getClearButton();
    }

    @Override
    public Dialog getRoot() {
        return changeUsernameView.getRoot();
    }
}
