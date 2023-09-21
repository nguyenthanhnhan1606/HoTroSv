package com.ntn.wedhotrots.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "livestreams")
public class Livestreams implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "ngay")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngay;
    @Basic(optional = false)
    @Column(name = "thoigian")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoigian;

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    @Basic
    @Column(name = "active")
    private byte active;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic(optional = false)
    @Column(name = "path")
    private String path;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLive")
    private Set<Questions> questionsSet;

    public Livestreams() {
    }

    public Livestreams(Integer id) {
        this.id = id;
    }

    public Livestreams(Integer id, String title, String description, Date ngay, Date thoigian) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ngay = ngay;
        this.thoigian = thoigian;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Date getThoigian() {
        return thoigian;
    }

    public void setThoigian(Date thoigian) {
        this.thoigian = thoigian;
    }

    @JsonIgnore
    public Set<Questions> getQuestionsSet() {
        return questionsSet;
    }

    public void setQuestionsSet(Set<Questions> questionsSet) {
        this.questionsSet = questionsSet;
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
        if (!(object instanceof Livestreams)) {
            return false;
        }
        Livestreams other = (Livestreams) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dht.pojo.Livestreams[ id=" + id + " ]";
    }

}

