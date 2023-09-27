package com.ntn.wedhotrots.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reply")
public class Reply implements Serializable {

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
    @Column(name = "ngayreply")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date ngayreply;

    @JsonIgnore
    @JoinColumn(name = "id_comment", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Comments idComment;
    @JoinColumn(name = "id_advisor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User idAdvisor;

    public Reply() {
    }

    public Reply(Integer id) {
        this.id = id;
    }

    public Reply(Integer id, String content, Date ngayreply) {
        this.id = id;
        this.content = content;
        this.ngayreply = ngayreply;
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

    public Date getNgayreply() {
        return ngayreply;
    }

    public void setNgayreply(Date ngayreply) {
        this.ngayreply = ngayreply;
    }

    public Comments getIdComment() {
        return idComment;
    }

    public void setIdComment(Comments idComment) {
        this.idComment = idComment;
    }

    public User getIdAdvisor() {
        return idAdvisor;
    }

    public void setIdAdvisor(User idAdvisor) {
        this.idAdvisor = idAdvisor;
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
        if (!(object instanceof Reply)) {
            return false;
        }
        Reply other = (Reply) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dht.pojo.Reply[ id=" + id + " ]";
    }

}
