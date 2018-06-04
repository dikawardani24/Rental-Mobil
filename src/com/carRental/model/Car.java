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

/**
 *
 * @author dika
 */
@Entity
@Table(name = "car")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Car.findByNoPlat", query = "SELECT c FROM Car c WHERE c.noPlat = :noPlat"), 
    @NamedQuery(name = "Car.findByStatus", query = "SELECT c FROM Car c WHERE c.status = :status"), 
    @NamedQuery(name = "Car.countByStatus", query = "SELECT COUNT(c) FROM Car c WHERE c.status = :status")
})
public class Car extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcar")
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "no_plat")
    private String noPlat;

    @Basic(optional = false)
    @Column(name = "warna")
    private String warna;

    @Basic(optional = false)
    @Column(name = "no_rangka")
    private String noRangka;

    @Basic(optional = false)
    @Column(name = "no_mesin")
    private String noMesin;

    @Basic(optional = false)
    @Column(name = "harga_sewa")
    private double hargaSewa;

    @ManyToOne
    @JoinColumn(name = "idmerk")
    private Merk merk;

    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    public String getNoPlat() {
        return noPlat;
    }

    public void setNoPlat(String noPlat) {
        this.noPlat = noPlat;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getNoRangka() {
        return noRangka;
    }

    public void setNoRangka(String noRangka) {
        this.noRangka = noRangka;
    }

    public String getNoMesin() {
        return noMesin;
    }

    public void setNoMesin(String noMesin) {
        this.noMesin = noMesin;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(double hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

    public Merk getMerk() {
        return merk;
    }

    public void setMerk(Merk merk) {
        this.merk = merk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return super.hashCode() + 1;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }

        if (!(other instanceof Car)) {
            return false;
        }

        Car otherCar = (Car) other;

        return id.equals(otherCar.id) &&
                noPlat.equals(otherCar.noPlat) &&
                warna.equals(otherCar.warna) &&
                noRangka.equals(otherCar.noRangka) &&
                noMesin.equals(otherCar.noMesin) &&
                hargaSewa == otherCar.hargaSewa &&
                merk.equals(otherCar.merk) &&
                status.equals(otherCar.status);
    }
}
