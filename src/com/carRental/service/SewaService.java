package com.carRental.service;

import com.carRental.model.Sewa;
import com.dika.database.DatabaseService;
import java.util.Date;
import java.util.List;

interface SewaService extends DatabaseService<Integer, Sewa> {
    List<Sewa> findsBy(Date start, Date end, int firstResult, int maxResults);
    
    int countBy(Date start, Date end);
}
