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
import com.dika.security.MD5Encryption;
import com.dika.view.component.*;
import org.jetbrains.annotations.NotNull;

/**
 * @author dika
 */
public final class ChangePasswordActivity extends InputActivity<ChangePasswordView> implements ChangePasswordView {
    private final ChangePasswordView changePasswordView = new ChangePasswordViewImpl();
    private final User user = Session.getInstance().getUser();

    private void updatePassword() {
        if (!validateInput()) return;

        String newPassword = new String(getNewPasswordField().getPassword());
        String retypePassword = new String(getRetypePasswordField().getPassword());

        if (!newPassword.equals(retypePassword)) {
            showNotifOn(getRetypePasswordField(), "Password yang anda masukkan tidak sama");
            return;
        }

        String oldPassword = user.getPassword();
        execute(new UserServiceImpl(), userService -> {
            try {
                MD5Encryption encryption = new MD5Encryption();
                String encrypted = encryption.secure(newPassword);
                user.setPassword(encrypted);
                userService.update(user);
                showSucceed("Password berhasil diperbaharui");
            } catch (Exception e) {
                user.setPassword(oldPassword);
                showFailed("Password gagal diperbaharui");
            }

            return userService;
        });
    }

    @Override
    public void clear() {
        getNewPasswordField().clear();
        getRetypePasswordField().clear();
    }

    @NotNull
    @Override
    public ChangePasswordView getView() {
        return changePasswordView;
    }

    @Override
    protected void initListener(ChangePasswordView changePasswordView) {
        getUsernameField().setText(user.getUsername());
        getIdKaryawanField().setText(String.valueOf(user.getKaryawan().getId()));

        getShowPasssCheckBox().addActionListener(evt -> {
            boolean show = getShowPasssCheckBox().isSelected();
            getNewPasswordField().showPassword(show);
            getRetypePasswordField().showPassword(show);
        });

        getClearButton().addActionListener(evt -> clear());

        getCancelButton().addActionListener(evt -> stop());

        getSaveButton().addActionListener(evt -> updatePassword());
    }

    @Override
    public TextField getUsernameField() {
        return changePasswordView.getUsernameField();
    }

    @Override
    public TextField getIdKaryawanField() {
        return changePasswordView.getIdKaryawanField();
    }

    @Override
    public PasswordField getNewPasswordField() {
        return changePasswordView.getNewPasswordField();
    }

    @Override
    public PasswordField getRetypePasswordField() {
        return changePasswordView.getRetypePasswordField();
    }

    @Override
    public CheckBox getShowPasssCheckBox() {
        return changePasswordView.getShowPasssCheckBox();
    }

    @Override
    public Button getSaveButton() {
        return changePasswordView.getSaveButton();
    }

    @Override
    public Button getCancelButton() {
        return changePasswordView.getCancelButton();
    }

    @Override
    public Button getClearButton() {
        return changePasswordView.getClearButton();
    }

    @Override
    public Dialog getRoot() {
        return changePasswordView.getRoot();
    }
}
