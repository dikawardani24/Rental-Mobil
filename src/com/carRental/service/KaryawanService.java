package com.carRental.service;

import com.carRental.model.Karyawan;
import com.dika.database.DatabaseService;

interface KaryawanService extends DatabaseService<Integer, Karyawan> {
    Karyawan findBy(String noKtp);
}
