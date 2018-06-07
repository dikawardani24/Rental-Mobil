package com.carRental.activity.main.sewa;

import com.alee.laf.menu.WebMenuItem;
import com.carRental.model.Car;
import com.carRental.service.CarServiceImpl;
import com.carRental.util.CarStatus;
import com.carRental.activity.tableModel.ChooseCarTableModel;
import com.dika.activity.Activity;
import com.dika.activity.service.OnResumedAction;
import com.dika.activity.service.OnStartedAction;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.custom.PagingTableView;
import com.dika.view.custom.PagingTableViewAction;
import com.dika.view.custom.PagingTableViewService;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChooseCarActivity extends Activity<ChooseCarView> implements ChooseCarView, PagingTableViewService {
    private final ChooseCarView view = new ChooseCarViewImpl();
    private ChooseCarTableModel tableModel;
    private PagingTableViewAction pagingTableViewAction;
    private OnChosenCar onChosenCar;

    private void chooseCar() {
        if (onChosenCar == null) return;

        Car car = getCarOnSelectedRow();
        onChosenCar.run(car);
        stop();
    }

    private Car getCarOnSelectedRow() {
        int selectedRow = getPagingTableView().getTable().getSelectedRow();

        if (selectedRow >= 0) {
            return tableModel.getEntities().get(selectedRow);
        } else {
            return null;
        }
    }

    public void setOnChosenCar(OnChosenCar onChosenCar) {
        this.onChosenCar = onChosenCar;
    }

    @Override
    public int countData() {
        return execute(new CarServiceImpl(), carService -> carService.countBy("Ada"));
    }

    @Override
    public void insertData(int firstResult, int maxResults) {
        execute(new CarServiceImpl(), carService -> {
            List<Car> cars = carService.findBy(CarStatus.ADA, firstResult, maxResults);
            tableModel.clear();
            tableModel.insert(cars);
            return Unit.INSTANCE;
        });
    }

    @Override
    protected void initListener(ChooseCarView chooseCarView) {
        tableModel = new ChooseCarTableModel(getPagingTableView().getTable());
        pagingTableViewAction = new PagingTableViewAction(this, getPagingTableView(), 100);

        getChooseCarMenuItem().addActionListener(e -> chooseCar());

        getCancelButton().addActionListener(e -> stop());

        add((OnStartedAction) activity -> pagingTableViewAction.toFirstPage());
        add((OnResumedAction) activity -> pagingTableViewAction.refreshPage());
    }

    public interface OnChosenCar {
        void run(Car car);
    }

    @NotNull
    @Override
    public Activity<?> getActivity() {
        return this;
    }

    @Override
    public WebMenuItem getChooseCarMenuItem() {
        return view.getChooseCarMenuItem();
    }

    @Override
    public PagingTableView getPagingTableView() {
        return view.getPagingTableView();
    }

    @Override
    public Button getCancelButton() {
        return view.getCancelButton();
    }

    @NotNull
    @Override
    public ChooseCarView getView() {
        return view;
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
