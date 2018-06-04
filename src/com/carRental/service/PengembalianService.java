package com.carRental.service;

import com.carRental.model.Pengembalian;
import com.dika.database.DatabaseService;
import java.util.Date;
import java.util.List;

interface PengembalianService extends DatabaseService<Integer, Pengembalian> {
    List<Pengembalian> findsBy(Date start, Date end, int firstResult, int maxResults);
    
    int countBy(Date start, Date end);
}
