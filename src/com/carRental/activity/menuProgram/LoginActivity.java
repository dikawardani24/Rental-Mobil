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
import com.carRental.activity.MainActivity;
import com.carRental.model.User;
import com.carRental.service.UserServiceImpl;
import com.dika.SystemException;
import com.dika.activity.InputActivity;
import com.dika.activity.service.OnResumedAction;
import com.dika.database.exception.NoResultException;
import com.dika.security.MD5Encryption;
import com.dika.view.component.*;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author dika
 */
public final class LoginActivity extends InputActivity<LoginView> implements LoginView {
    private final LoginView loginView = new LoginViewImpl();
    
    private void login() {
        if (!validateInput()) {
            return;
        }

        PopOver progressPopOver = showProgress("Mencoba Login", "Menemukan user...");

        Thread loginThread = new Thread(() -> {
            String username = getUsernameField().getText();

            execute(new UserServiceImpl(), userService -> {
                try {
                    User user = userService.findBy(username);
                    String password = new String(getPasswordField().getPassword());
                    MD5Encryption encryption = new MD5Encryption();
                    boolean grandted = encryption.isMatched(user.getPassword(), password);

                    if (grandted) {
                        Session.getInstance().setUser(user);
                        stopThenStart(MainActivity.class);
                        progressPopOver.dispose();
                    } else {
                        progressPopOver.dispose();
                        showFailed("Password yang anda masukkan salah");
                    }
                } catch (SystemException e) {
                    progressPopOver.dispose();

                    if (e instanceof NoResultException) {
                        showInfo("Tidak menemukan user dengan username : "+username);
                        return Unit.INSTANCE;
                    }

                    throw e;
                }

                return Unit.INSTANCE;
            });
        });

        loginThread.setName("Login Thread");
        loginThread.start();
    }

    @Override
    protected void initListener(LoginView loginView) {
        getShowPasswordCheckBox().addActionListener(evt -> {
            boolean show = getShowPasswordCheckBox().isSelected();
            getPasswordField().showPassword(show);
        });

        getCancelButton().addActionListener(evt -> stop());

        getLoginButton().addActionListener(evt -> login());

        add((OnResumedAction) activity -> clear());
    }

    @NotNull
    @Override
    public LoginView getView() {
        return loginView;
    }

    @Override
    public TextField getUsernameField() {
        return loginView.getUsernameField();
    }

    @Override
    public PasswordField getPasswordField() {
        return loginView.getPasswordField();
    }

    @Override
    public CheckBox getShowPasswordCheckBox() {
        return loginView.getShowPasswordCheckBox();
    }

    @Override
    public Button getLoginButton() {
        return loginView.getLoginButton();
    }

    @Override
    public Button getCancelButton() {
        return loginView.getCancelButton();
    }

    @Override
    public Dialog getRoot() {
        return loginView.getRoot();
    }
}
