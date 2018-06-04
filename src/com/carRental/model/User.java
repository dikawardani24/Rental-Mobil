package com.carRental.model;

import com.dika.database.AbstractEntity;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findByUsername", 
            query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByIdkaryawan", 
            query = "SELECT u FROM User u WHERE u.karyawan.id = :idkaryawan"),
    @NamedQuery(name = "User.countByKaryawan", 
            query = "SELECT COUNT(u) FROM User u WHERE u.karyawan.id = :idkaryawan")
})
public class User extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iduser")
    private Integer iduser;

    @Basic(optional = false)
    @Column(name = "username")
    private String username;

    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "idkaryawan")
    private Karyawan karyawan;

    public User() {
    }

    public User(Integer iduser) {
        this.iduser = iduser;
    }

    public User(int iduser, String username, String password, Karyawan karyawan) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.karyawan = karyawan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    @Override
    public void setId(Integer iduser) {
        this.iduser = iduser;
    }

    @Nullable
    @Override
    public Integer getId() {
        return iduser;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 7;
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object) && (object instanceof User);
    }
}
