package com.carRental.activity.menuDataManager.car;

import com.carRental.util.CarStatus;
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

import java.util.List;

public final class AddCarActivity extends InputActivity<AddCarView> implements AddCarView {
    private final AddCarView view = new AddCarViewImpl();
    private List<Merk> merks;

    private void saveNewCar() {
        if (!validateInput()) return;

        if (!validateNoPlat()) return;

        Car car = new Car();

        car.setNoPlat(getNoPlatField().getText());
        car.setWarna(getWarnaField().getText());
        car.setNoRangka(getNoRangkaField().getText());
        car.setNoMesin(getNoMesinField().getText());
        car.setHargaSewa(NumberHelper.INSTANCE.toDouble(getHargaSewaField().getValue()));
        car.setMerk(merks.get(getMerkCmbBox().getSelectedIndex()));
        car.setStatus(CarStatus.ADA.getText());

        execute(new CarServiceImpl(), "Data Karyawan Berhasil Disimpan", "Data Karyawan Gagal Disimpan",
                carService -> {
                    carService.create(car);
                    Activity<?> parent = getParent();
                    if (parent instanceof CarManagerActivity) {
                        ((CarManagerActivity) parent).refresh();
                    }
                    return carService;
                });
    }

    private void loadPilihanMerk() {
        if (merks != null && !merks.isEmpty()) {
            merks.clear();
            getMerkCmbBox().removeAllItems();
        }
        
        merks = execute(new MerkServiceImpl(), MerkServiceImpl::findAll);
        merks.forEach(merk -> getMerkCmbBox().addItem(merk.getNama()));
    }

    private boolean validateNoPlat() {
        String noPlat = getNoPlatField().getText();
        Car car = execute(new CarServiceImpl(), carService -> {
            try {
                return carService.findBy(noPlat);
            } catch (Exception e) {
                Logger.INSTANCE.printError(e);
                return null;
            }
        });

        if (car != null) {
            showNotifOn(getNoPlatField(), "No. Plat Ini Sudah Terdaftar");
            return false;
        }

        return true;
    }

    @Override
    protected void initListener(AddCarView addCarView) {
        getSaveButton().addActionListener(e -> saveNewCar());

        getClearButton().addActionListener(e -> clear());

        getCancelButton().addActionListener(e -> stop());

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
    public AddCarView getView() {
        return view;
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
