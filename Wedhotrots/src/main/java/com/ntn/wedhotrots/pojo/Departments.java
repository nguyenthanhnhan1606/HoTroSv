package com.ntn.wedhotrots.pojo;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "departments")
public class Departments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tenkhoa")
    private String tenkhoa;
    @Basic(optional = false)
    @Column(name = "mota")
    private String mota;
    @Basic(optional = false)
    @Column(name = "motaCTDT")
    private String motaCTDT;
    @Basic(optional = false)
    @Column(name = "website")
    private String website;
    @Column(name = "video")
    private String video;
    @Basic(optional = false)
    @Column(name = "trungbinhdiem")
    private double trungbinhdiem;
    @Column(name = "active")
    private byte active;

    public Departments() {
    }

    public Departments(Integer id) {
        this.id = id;
    }

    public Departments(Integer id, String tenkhoa, String mota, String motaCTDT, String website, double trungbinhdiem) {
        this.id = id;
        this.tenkhoa = tenkhoa;
        this.mota = mota;
        this.motaCTDT = motaCTDT;
        this.website = website;
        this.trungbinhdiem = trungbinhdiem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenkhoa() {
        return tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getMotaCTDT() {
        return motaCTDT;
    }

    public void setMotaCTDT(String motaCTDT) {
        this.motaCTDT = motaCTDT;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public double getTrungbinhdiem() {
        return trungbinhdiem;
    }

    public void setTrungbinhdiem(double trungbinhdiem) {
        this.trungbinhdiem = trungbinhdiem;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departments)) {
            return false;
        }
        Departments other = (Departments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntn.wedhotrots.pojo.Departments[ id=" + id + " ]";
    }

}

