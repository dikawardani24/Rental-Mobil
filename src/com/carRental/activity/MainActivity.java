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
package com.carRental.activity;

import com.carRental.activity.main.MainController;
import com.carRental.activity.main.PengembalianController;
import com.carRental.activity.main.SewaController;
import com.carRental.activity.menuAbout.AppsAboutActivity;
import com.carRental.activity.menuAbout.CompanyAboutActivity;
import com.carRental.activity.menuAbout.UnivAboutActivity;
import com.carRental.activity.menuDataManager.*;
import com.carRental.activity.menuProgram.ChangePasswordActivity;
import com.carRental.activity.menuProgram.ChangeUsernameActivity;
import com.carRental.activity.menuProgram.LoginActivity;
import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.database.DatabaseService;
import com.dika.report.Report;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 *
 * @author dika
 */
public class MainActivity extends InputActivity<MainView>{
    private final MainView frame = new MainViewImpl();
    
    @Override
    protected void initListener(MainView view) {
        view.getChangeUsernameMenu().addActionListener(evt -> startOther(ChangeUsernameActivity.class));

        view.getChangePasswordMenu().addActionListener(evt -> startOther(ChangePasswordActivity.class));

        view.getLogoutMenu().addActionListener(evt -> stopThenStart(LoginActivity.class));

        view.getExitMenu().addActionListener(evt -> stop());

        view.getMdKaryawanMenu().addActionListener(evt -> startOther(KaryawanManagerActivity.class));

        view.getMdUserMenu().addActionListener(evt -> startOther(UserManagerActivity.class));

        view.getMdCarMenu().addActionListener(evt -> startOther(CarManagerActivity.class));

        view.getMdMerkMenu().addActionListener(evt -> startOther(MerkManagerActivity.class));

        view.getMdPelangganMenu().addActionListener(evt -> startOther(PelangganManagerActivity.class));

        view.getMdTransaksiMenu().addActionListener(evt -> startOther(TransaksiManagerActivity.class));

        view.getAbProgramMenu().addActionListener(evt -> startOther(AppsAboutActivity.class));

        view.getAbLaundryMenu().addActionListener(evt -> startOther(CompanyAboutActivity.class));

        view.getAbUnivMenu().addActionListener(evt -> startOther(UnivAboutActivity.class));


        SewaController sewaController = new SewaController(this, view.getSewaContainer());
        sewaController.init();
        
        PengembalianController pengembalianController = new PengembalianController(this, 
                view.getPengembalianContainer());
        pengembalianController.init();
    }
    
    public void showInfo(MainController mainController, String message) {
        showInfo(message);
    }

    public void showFailed(MainController mainController, String message) {
        showFailed(message);
    }

    public void showSucceed(MainController mainController, String message) {
        showSucceed(message);
    }

    public void showEmptyNotifOn(MainController mainController, Component component) {
        showEmptyNotifOn(component);
    }

    public  <R, S extends DatabaseService<?, ?>> R execute(MainController mainController, 
            S dbService, Function1<? super S, ? extends R> block) {
        return super.execute(dbService, block);
    }

    public  <R, S extends DatabaseService<?, ?>> R execute(MainController mainController, 
            S dbService, String onSucceed, String onFailed,
                                                           Function1<? super S, ? extends R> block) {
        return super.execute(dbService, onSucceed, onFailed, block);
    }

    public void showReport(MainController mainController, Report report) {
        showReport(report);
    }

    @NotNull
    public <A extends Activity<?>> A startOther(MainController mainController, Class<A> activityClass) {
        return super.startOther(activityClass);
    }
    
    @NotNull
    @Override
    public MainView getView() {
        return frame;
    }

}
