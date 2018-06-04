package com.carRental.service;

import com.carRental.util.CarStatus;
import com.carRental.model.Car;
import com.dika.database.DatabaseService;

import java.util.List;

interface CarService extends DatabaseService<Integer, Car> {
    Car findBy(String noPlat);
    
    List<Car> findBy(CarStatus carStatus, int firstResult, int maxResult);

    int countBy(String status);
}
