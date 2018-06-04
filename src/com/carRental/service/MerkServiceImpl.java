package com.carRental.service;

import com.carRental.model.Merk;
import com.dika.database.DatabaseServiceImpl;
import org.jetbrains.annotations.NotNull;

public class MerkServiceImpl extends DatabaseServiceImpl<Integer, Merk>
        implements MerkService {

    @NotNull
    @Override
    protected Class<Merk> getEntityKClass() {
        return Merk.class;
    }

    @Override
    public Merk findBy(String nama) {
        return findByNamedQuery("Merk.findByNama", parameters -> {
            parameters.put("nama", nama);
            return parameters;
        });
    }
    
}
