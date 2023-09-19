package com.ntn.wedhotrots.pojo;

import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "answers")
public class Answers implements Serializable {

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
    @Column(name = "ngaytraloi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaytraloi;
    @JoinColumn(name = "id_question", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Questions idQuestion;
    @JoinColumn(name = "id_advisor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User idAdvisor;

    public Answers() {
    }

    public Answers(Integer id) {
        this.id = id;
    }

    public Answers(Integer id, String content, Date ngaytraloi) {
        this.id = id;
        this.content = content;
        this.ngaytraloi = ngaytraloi;
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

    public Date getNgaytraloi() {
        return ngaytraloi;
    }

    public void setNgaytraloi(Date ngaytraloi) {
        this.ngaytraloi = ngaytraloi;
    }

    public Questions getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Questions idQuestion) {
        this.idQuestion = idQuestion;
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
        if (!(object instanceof Answers)) {
            return false;
        }
        Answers other = (Answers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dht.pojo.Answers[ id=" + id + " ]";
    }

}
