package com.carRental.activity.menuDataManager;

import com.alee.laf.menu.WebMenuItem;
import com.carRental.activity.menuDataManager.car.AddCarActivity;
import com.carRental.activity.menuDataManager.car.DeleteCarActivity;
import com.carRental.activity.menuDataManager.car.UpdateCarActivity;
import com.carRental.model.Car;
import com.carRental.report.CarReport;
import com.carRental.service.CarServiceImpl;
import com.carRental.activity.tableModel.CarTableModel;
import com.dika.activity.Activity;
import com.dika.activity.service.OnResumedAction;
import com.dika.activity.service.OnStartedAction;
import com.dika.view.component.Button;
import com.dika.view.component.Frame;
import com.dika.view.component.Table;
import com.dika.view.custom.PagingTableView;
import com.dika.view.custom.PagingTableViewAction;
import com.dika.view.custom.PagingTableViewService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class CarManagerActivity extends Activity<CarManagerView> implements CarManagerView, PagingTableViewService {
    private final CarManagerView view = new CarManagerViewImpl();
    private CarTableModel tableModel;
    private PagingTableViewAction pagingTableViewAction;

    public void refresh() {
        pagingTableViewAction.refreshPage();
    }

    private void printDataCar() {
        if (tableModel.getEntities().isEmpty()) {
            showInfo("Daftar Mobil masih kosong di table");
            return;
        }

        CarReport report = new CarReport();
        report.setCars(tableModel.getEntities());
        showReport(report);
    }

    private Car getCarOnSelectedRow() {
        int selectedRow = getPagingTableView().getTable().getSelectedRow();

        if (selectedRow >= 0) {
            return tableModel.getEntities().get(selectedRow);
        } else {
            return null;
        }
    }

    private void updateCar() {
        Car car = getCarOnSelectedRow();
        if (car != null) {
            UpdateCarActivity activity = startOther(UpdateCarActivity.class);
            activity.setCar(car);
        } else {
            showInfo("Tidak ada baris terpilih");
        }
    }

    private void deleteCar() {
        Car car = getCarOnSelectedRow();
        if (car != null) {
            DeleteCarActivity activity = startOther(DeleteCarActivity.class);
            activity.setCar(car);
        } else {
            showInfo("Tidak ada baris terpilih");
        }
    }

    @Override
    protected void initListener(CarManagerView carManagerView) {
        Table table = getPagingTableView().getTable();
        
        table.setEditable(false);
        tableModel = new CarTableModel(table);
        pagingTableViewAction = new PagingTableViewAction(this, getPagingTableView(), 50);

        getAddNewCarButton().addActionListener(e -> startOther(AddCarActivity.class));

        getPrintButton().addActionListener(e -> printDataCar());

        getUpdateKaryawanMenuItem().addActionListener(e -> updateCar());

        getDeleteKaryawanMenuItem().addActionListener(e -> deleteCar());

        add((OnStartedAction) activity -> pagingTableViewAction.toFirstPage());
        add((OnResumedAction) activity -> pagingTableViewAction.refreshPage());
    }

    @Override
    public int countData() {
        return execute(new CarServiceImpl(), CarServiceImpl::count);
    }

    @Override
    public void insertData(int firstResult, int maxResults) {
        execute(new CarServiceImpl(), carService -> {
            List<Car> cars = carService.findAll(maxResults, firstResult);
            tableModel.clear();
            tableModel.insert(cars);
            return carService;
        });
    }

    @Override
    public Button getAddNewCarButton() {
        return view.getAddNewCarButton();
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
    public WebMenuItem getUpdateKaryawanMenuItem() {
        return view.getUpdateKaryawanMenuItem();
    }

    @Override
    public WebMenuItem getDeleteKaryawanMenuItem() {
        return view.getDeleteKaryawanMenuItem();
    }

    @NotNull
    @Override
    public CarManagerView getView() {
        return view;
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
