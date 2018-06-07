/*
 * Copyright 2018 dika.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.carRental.model;

import com.dika.database.AbstractEntity;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *
 * @author dika
 */
@Entity
@Table(name = "pengembalian")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Pengembalian.findBySewa", query = "SELECT p FROM Pengembalian p WHERE p.sewa = :sewa"),
        @NamedQuery(name = "Pengembalian.findBetween", query = "SELECT p FROM Pengembalian p WHERE (p.tglTransaksi BETWEEN :start AND :end)"),
        @NamedQuery(name = "Pengembalian.countBetween", query = "SELECT COUNT(p) FROM Pengembalian p WHERE (p.tglTransaksi BETWEEN :start AND :end)")

})
public class Pengembalian extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpengembalian")
    @Basic(optional = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idsewa")
    private Sewa sewa;

    @ManyToOne
    @JoinColumn(name = "idkaryawan")
    private Karyawan penerimaKembali;

    @Basic(optional = false)
    @Column(name = "tgl_transaki")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglTransaksi;

    @Basic(optional = false)
    @Column(name = "overtime")
    private int overtime;

    public Sewa getSewa() {
        return sewa;
    }

    public void setSewa(Sewa sewa) {
        this.sewa = sewa;
    }

    public Karyawan getPenerimaKembali() {
        return penerimaKembali;
    }

    public void setPenerimaKembali(Karyawan penerimaKembali) {
        this.penerimaKembali = penerimaKembali;
    }

    public Date getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(Date tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    @Nullable
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 5;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) return false;

        if (!(other instanceof Pengembalian)) return false;

        Pengembalian otherPengembalian = (Pengembalian) other;
        return id.equals(otherPengembalian.id) &&
                sewa.equals(otherPengembalian.sewa) &&
                penerimaKembali.equals(otherPengembalian.penerimaKembali) &&
                tglTransaksi.equals(otherPengembalian.tglTransaksi) &&
                overtime == otherPengembalian.overtime;
    }
}
