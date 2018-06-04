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
@Table(name = "sewa")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Sewa.findBetween", query = "SELECT s FROM Sewa s WHERE (s.tglTransaksi BETWEEN :start AND :end)"),
        @NamedQuery(name = "Sewa.countBetween", query = "SELECT COUNT(s) FROM Sewa s WHERE (s.tglTransaksi BETWEEN :start AND :end)")

})
public class Sewa extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsewa")
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "tgl_transaki")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglTransaksi;

    @ManyToOne
    @JoinColumn(name = "idcar")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "idpelanggan")
    private Pelanggan pelanggan;

    @ManyToOne
    @JoinColumn(name = "idkaryawan")
    private Karyawan pemberiSewa;

    @Basic(optional = false)
    @Column(name = "lama_sewa")
    private int lamaSewa;

    @Basic(optional = false)
    @Column(name = "total_tagihan")
    private double totalTagihan;

    public Date getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(Date tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public Karyawan getPemberiSewa() {
        return pemberiSewa;
    }

    public void setPemberiSewa(Karyawan pemberiSewa) {
        this.pemberiSewa = pemberiSewa;
    }

    public int getLamaSewa() {
        return lamaSewa;
    }

    public void setLamaSewa(int lamaSewa) {
        this.lamaSewa = lamaSewa;
    }

    public double getTotalTagihan() {
        return totalTagihan;
    }

    public void setTotalTagihan(double totalTagihan) {
        this.totalTagihan = totalTagihan;
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
        return super.hashCode() + 6;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) return false;

        if (!(other instanceof Sewa)) return false;

        Sewa otherSewa = (Sewa) other;
        return id.equals(otherSewa.id)&&
                tglTransaksi.equals(otherSewa.tglTransaksi) &&
                car.equals(otherSewa.car) &&
                pelanggan.equals(otherSewa.pelanggan) &&
                pemberiSewa.equals(otherSewa.pemberiSewa) &&
                lamaSewa == otherSewa.lamaSewa &&
                totalTagihan == otherSewa.totalTagihan;
    }
}
