package com.carRental.service;

import com.carRental.model.Karyawan;
import com.dika.database.DatabaseServiceImpl;
import org.jetbrains.annotations.NotNull;

public class KaryawanServiceImpl extends DatabaseServiceImpl<Integer, Karyawan>
        implements KaryawanService {

    @NotNull
    @Override
    protected Class<Karyawan> getEntityKClass() {
        return Karyawan.class;
    }

    @Override
    public Karyawan findBy(String noKtp) {
        return findByNamedQuery("Karyawan.findByNoKtp", parameters-> {
            parameters.put("noKtp", noKtp);
            return parameters;
        });
    }
    
}
