package com.carRental.service;

import com.carRental.model.Karyawan;
import com.carRental.model.User;
import com.dika.database.DatabaseService;

interface UserService extends DatabaseService<Integer, User> {
    User findBy(String username);

    User findBy(Karyawan karyawan);
}
