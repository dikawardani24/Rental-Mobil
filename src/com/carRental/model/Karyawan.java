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
@Table(name = "karyawan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Karyawan.findByNoKtp", query = "SELECT k FROM Karyawan k WHERE k.noKtp = :noKtp")
})
public class Karyawan extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idkaryawan")
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;

    @Basic(optional = false)
    @Column(name = "alamat")
    private String alamat;

    @Basic(optional = false)
    @Column(name = "jenis_kelamin")
    private String jenisKelamin;

    @Basic(optional = false)
    @Column(name = "no_ktp")
    private String noKtp;

    @Basic(optional = false)
    @Column(name = "no_hp")
    private String noHp;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
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
        return super.hashCode()+2;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) return false;

        if (!(other instanceof Karyawan)) return false;

        Karyawan otherKaryawan = (Karyawan) other;
        return id.equals(otherKaryawan.id) &&
                nama.equals(otherKaryawan.nama) &&
                alamat.equals(otherKaryawan.alamat) &&
                jenisKelamin.equals(otherKaryawan.jenisKelamin) &&
                noKtp.equals(otherKaryawan.noKtp) &&
                noHp.equals(otherKaryawan.noHp);
    }
}
