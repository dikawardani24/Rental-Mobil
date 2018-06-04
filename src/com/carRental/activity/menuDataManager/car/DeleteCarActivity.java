package com.carRental.activity.menuDataManager.car;

import com.carRental.util.CarStatus;
import com.carRental.activity.menuDataManager.CarManagerActivity;
import com.carRental.model.Car;
import com.carRental.service.CarServiceImpl;
import com.dika.Logger;
import com.dika.activity.Activity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextField;
import com.dika.view.component.custom.CurrencyFormattedTextField;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class DeleteCarActivity extends Activity<DeleteCarView> implements DeleteCarView {
    private final DeleteCarView view = new DeleteCarViewImpl();
    private Car car;

    public void setCar(Car car) {
        SwingUtilities.invokeLater(() -> {
            this.car = car;
            getIdCarField().setText(String.valueOf(car.getId()));
            getNoPlatField().setText(car.getNoPlat());
            getWarnaField().setText(car.getWarna());
            getNoRangkaField().setText(car.getNoRangka());
            getNoMesinField().setText(car.getNoMesin());
            getHargaSewaField().setValue(car.getHargaSewa());
            getMerkField().setText(car.getMerk().getNama());
        });
    }

    private boolean grantedToDelete() {
        if (car == null) {
            showInfo("Tidak ada data mobil yang akan dihapus");
            return false;
        }

        if (car.getStatus().equals(CarStatus.SEDANG_DISEWA.getText())) {
            showInfo("Menghapus Data Mobil Yang Sedang Disewa Tidak Diijinkan");
            return false;
        }

        return true;
    }

    private void delete() {
        if (!grantedToDelete()) return;

        execute(new CarServiceImpl(), carService -> {
            try {
                carService.destroy(car);
                Activity<?> parent = getParent();
                if (parent instanceof CarManagerActivity) {
                    ((CarManagerActivity) parent).refresh();
                }
                showSucceed("Data Mobil Berhasil Dihapus");
                stop();
                return carService;
            } catch (Exception e) {
                Logger.INSTANCE.printError(e);
                showFailed("Data Mobil Gagal Dihapus");
            }
            return Unit.INSTANCE;
        });
    }

    @Override
    protected void initListener(DeleteCarView deleteCarView) {
        getCancelButton().addActionListener(e -> stop());
        getDeleteButton().addActionListener((e) -> delete());
        
    }

    @Override
    public TextField getIdCarField() {
        return view.getIdCarField();
    }

    @Override
    public TextField getNoPlatField() {
        return view.getNoPlatField();
    }

    @Override
    public TextField getWarnaField() {
        return view.getWarnaField();
    }

    @Override
    public TextField getNoRangkaField() {
        return view.getNoRangkaField();
    }

    @Override
    public TextField getNoMesinField() {
        return view.getNoMesinField();
    }

    @Override
    public CurrencyFormattedTextField getHargaSewaField() {
        return view.getHargaSewaField();
    }

    @Override
    public TextField getMerkField() {
        return view.getMerkField();
    }

    @Override
    public Button getDeleteButton() {
        return view.getDeleteButton();
    }

    @Override
    public Button getCancelButton() {
        return view.getCancelButton();
    }

    @NotNull
    @Override
    public DeleteCarView getView() {
        return view;
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
