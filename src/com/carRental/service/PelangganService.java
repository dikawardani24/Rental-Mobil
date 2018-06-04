package com.carRental.service;

import com.carRental.model.Pelanggan;
import com.dika.database.DatabaseService;

interface PelangganService extends DatabaseService<Integer, Pelanggan> {
    Pelanggan findBy(String noKtp);
}
