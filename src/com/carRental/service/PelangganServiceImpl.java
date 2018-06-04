package com.carRental.service;

import com.carRental.model.Pelanggan;
import com.dika.database.DatabaseServiceImpl;
import org.jetbrains.annotations.NotNull;


public class PelangganServiceImpl extends DatabaseServiceImpl<Integer, Pelanggan>
    implements PelangganService{

    @NotNull
    @Override
    protected Class<Pelanggan> getEntityKClass() {
        return Pelanggan.class;
    }

    @Override
    public Pelanggan findBy(String noKtp) {
        return findByNamedQuery("Pelanggan.findByNoKtp", parameters -> {
            parameters.put("noKtp", noKtp);
            return parameters;
        });
    }
}
