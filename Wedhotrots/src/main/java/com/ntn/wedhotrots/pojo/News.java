package com.ntn.wedhotrots.pojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "news")
public class News implements Serializable {

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
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;

    @Basic(optional = false)
    @Column(name = "trangthai")
    private String trangthai;

    public String getTrangthai() {
        return trangthai;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    @Column(name = "active")
    private byte active;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @Column(name = "ngaytao")
    @Temporal(TemporalType.DATE)
    private Date ngaytao;



    @Basic(optional = false)
    @Column(name = "ngaycapnhat")
    @Temporal(TemporalType.DATE)
    private Date ngaycapnhat;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNews")
    private Set<Comments> commentsSet;

    public News() {
    }

    public News(Integer id) {
        this.id = id;
    }

    public News(Integer id, String title, String content, String type, String image, Date ngaytao, Date ngaycapnhat) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.image = image;
        this.ngaytao = ngaytao;
        this.ngaycapnhat = ngaycapnhat;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Date getNgaycapnhat() {
        return ngaycapnhat;
    }

    public void setNgaycapnhat(Date ngaycapnhat) {
        this.ngaycapnhat = ngaycapnhat;
    }


    @Transient
    public Set<Comments> getCommentsSet() {
        return commentsSet;
    }

    public void setCommentsSet(Set<Comments> commentsSet) {
        this.commentsSet = commentsSet;
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
        if (!(object instanceof News)) {
            return false;
        }
        News other = (News) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dht.pojo.News[ id=" + id + " ]";
    }

}

