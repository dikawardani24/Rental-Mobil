package com.carRental.service;

import com.carRental.model.Sewa;
import com.dika.database.DatabaseServiceImpl;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class SewaServiceImpl extends DatabaseServiceImpl<Integer, Sewa>
        implements SewaService {

    @NotNull
    @Override
    protected Class<Sewa> getEntityKClass() {
        return Sewa.class;
    }

    @Override
    public List<Sewa> findsBy(Date start, Date end, int firstResult, int maxResults) {
        return findsByNamedQuery("Sewa.findBetween", maxResults, firstResult, parameters -> {
            parameters.put("start", start);
            parameters.put("end", end);
            return parameters;
        });
    }

    @Override
    public int countBy(Date start, Date end) {
        return countByNamedQuery("Sewa.countBetween", parameters -> {
            parameters.put("start", start);
            parameters.put("end", end);
            return parameters;
        });
    }

}
