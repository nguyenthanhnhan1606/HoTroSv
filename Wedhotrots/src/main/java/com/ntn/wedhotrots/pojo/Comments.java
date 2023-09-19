package com.ntn.wedhotrots.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "comments")
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @Column(name = "ngaybinhluan")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaybinhluan;
    @JoinColumn(name = "id_news", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private News idNews;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User idUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComment")
    private Set<Reply> replySet;

    public Comments() {
    }

    public Comments(Integer id) {
        this.id = id;
    }

    public Comments(Integer id, String content, Date ngaybinhluan) {
        this.id = id;
        this.content = content;
        this.ngaybinhluan = ngaybinhluan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getNgaybinhluan() {
        return ngaybinhluan;
    }

    public void setNgaybinhluan(Date ngaybinhluan) {
        this.ngaybinhluan = ngaybinhluan;
    }

    public News getIdNews() {
        return idNews;
    }

    public void setIdNews(News idNews) {
        this.idNews = idNews;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @JsonIgnore
    public Set<Reply> getReplySet() {
        return replySet;
    }

    public void setReplySet(Set<Reply> replySet) {
        this.replySet = replySet;
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
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dht.pojo.Comments[ id=" + id + " ]";
    }

}

