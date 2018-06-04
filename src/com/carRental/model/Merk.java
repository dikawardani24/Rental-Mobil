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
@Table(name = "merk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Merk.findByNama", query = "SELECT m FROM Merk m WHERE m.nama = :nama")
})
public class Merk extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmerk")
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
        return super.hashCode() + 3;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) return false;

        if (!(other instanceof Merk)) return false;

        Merk otherMerk = (Merk) other;
        return id.equals(otherMerk.id) &&
                nama.equals(otherMerk.nama);
    }
}
