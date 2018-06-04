package com.carRental.activity.menuDataManager.car;

import com.carRental.activity.menuDataManager.CarManagerActivity;
import com.carRental.model.Car;
import com.carRental.model.Merk;
import com.carRental.service.CarServiceImpl;
import com.carRental.service.MerkServiceImpl;
import com.dika.Logger;
import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.util.NumberHelper;
import com.dika.view.component.Button;
import com.dika.view.component.ComboBox;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextField;
import com.dika.view.component.custom.CurrencyFormattedTextField;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;

public final class UpdateCarActivity extends InputActivity<UpdateCarView> implements UpdateCarView {
    private final UpdateCarView view = new UpdateCarViewImpl();
    private Car car;
    private List<Merk> merks;

    public void setCar(Car car) {
        SwingUtilities.invokeLater(() -> {
            this.car = car;
            getNoPlatField().setText(car.getNoPlat());
            getWarnaField().setText(car.getWarna());
            getNoRangkaField().setText(car.getNoRangka());
            getNoMesinField().setText(car.getNoMesin());
            getHargaSewaField().setValue(car.getHargaSewa());
            getMerkCmbBox().setSelectedItem(car.getMerk());
        });
    }

    private void loadPilihanMerk() {
        merks = execute(new MerkServiceImpl(), MerkServiceImpl::findAll);

        if (merks != null) {
            if (!merks.isEmpty()) {
                merks.clear();
                getMerkCmbBox().removeAllItems();
            }

            merks.forEach(merk -> getMerkCmbBox().addItem(merk.getNama()));
        }
    }

    private void updateDataCar() {
        if (!validateInput())  return;

        try {
            Car old = (Car) car.clone();

            car.setNoPlat(getNoPlatField().getText());
            car.setWarna(getWarnaField().getText());
            car.setNoRangka(getNoRangkaField().getText());
            car.setNoMesin(getNoMesinField().getText());
            car.setHargaSewa(NumberHelper.INSTANCE.toDouble(getHargaSewaField().getValue()));
            car.setMerk(merks.get(getMerkCmbBox().getSelectedIndex()));

            if (old.equals(car)) {
                showInfo("Tidak ada perubahan data dilakukan");
                return;
            }

            execute(new CarServiceImpl(), "Data Mobil Berhasil Disimpan", "Data Mobil Gagal Disimpan",
                    carService -> {
                        carService.update(car);
                        Activity<?> parent = getParent();
                        if (parent instanceof CarManagerActivity) {
                            ((CarManagerActivity) parent).refresh();
                        }
                        return carService;
                    });
        } catch (CloneNotSupportedException e) {
            Logger.INSTANCE.printError(e);
        }
    }

    @Override
    protected boolean validateInput() {
        if (car == null) {
            showFailed("Tidak ada data mobil yang akan diupdate");
            return false;
        }
        return super.validateInput();
    }

    @Override
    protected void initListener(UpdateCarView updateCarView) {
        getSaveButton().addActionListener(e -> updateDataCar());
        getCancelButton().addActionListener(e -> stop());
        getClearButton().addActionListener(e -> clear());

        addStartedAction(() -> {
            loadPilihanMerk();
            return Unit.INSTANCE;
        });

        addResumedAction(() -> {
            loadPilihanMerk();
            return Unit.INSTANCE;
        });
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
    public ComboBox<String> getMerkCmbBox() {
        return view.getMerkCmbBox();
    }

    @Override
    public Button getSaveButton() {
        return view.getSaveButton();
    }

    @Override
    public Button getCancelButton() {
        return view.getCancelButton();
    }

    @Override
    public Button getClearButton() {
        return view.getClearButton();
    }

    @NotNull
    @Override
    public UpdateCarView getView() {
        return view;
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
