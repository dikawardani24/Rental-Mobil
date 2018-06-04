package com.carRental.service;

import com.carRental.util.CarStatus;
import com.carRental.model.Car;
import com.dika.database.DatabaseServiceImpl;

import java.util.List;

import org.jetbrains.annotations.NotNull;

public class CarServiceImpl extends DatabaseServiceImpl<Integer, Car>
        implements CarService {

    @NotNull
    @Override
    protected Class<Car> getEntityKClass() {
        return Car.class;
    }

    @Override
    public Car findBy(String noPlat) {
        return findByNamedQuery("Car.findByNoPlat", parameters -> {
            parameters.put("noPlat", noPlat);
            return parameters;
        });
    }

    @Override
    public List<Car> findBy(CarStatus status, int firstResult, int maxResult) {
        return findsByNamedQuery("Car.findByStatus", maxResult, firstResult, parameters -> {
            parameters.put("status", status.getText());
            return parameters;
        });
    }

    @Override
    public int countBy(String status) {
        return countByNamedQuery("Car.countByStatus", parameters -> {
            parameters.put("status", status);
            return parameters;
        });
    }
}
