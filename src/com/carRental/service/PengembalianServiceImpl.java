package com.carRental.service;

import com.carRental.model.Pengembalian;
import com.dika.database.DatabaseServiceImpl;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class PengembalianServiceImpl extends DatabaseServiceImpl<Integer, Pengembalian>
        implements PengembalianService {

    @NotNull
    @Override
    protected Class<Pengembalian> getEntityKClass() {
        return Pengembalian.class;
    }

    @Override
    public List<Pengembalian> findsBy(Date start, Date end, int firstResult, int maxResults) {
        return findsByNamedQuery("Pengembalian.findBetween", maxResults, firstResult, parameters -> {
            parameters.put("start", start);
            parameters.put("end", end);
            return parameters;
        });
    }

    @Override
    public int countBy(Date start, Date end) {
        return countByNamedQuery("Pengembalian.countBetween", parameters -> {
            parameters.put("start", start);
            parameters.put("end", end);
            return parameters;
        });
    }
}
