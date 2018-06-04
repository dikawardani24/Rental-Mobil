package com.carRental.service;

import com.carRental.model.Merk;
import com.dika.database.DatabaseService;

interface MerkService extends DatabaseService<Integer, Merk> {
    Merk findBy(String nama);
}
