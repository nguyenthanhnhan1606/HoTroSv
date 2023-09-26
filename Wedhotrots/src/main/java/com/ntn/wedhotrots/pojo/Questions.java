package com.ntn.wedhotrots.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;



import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Questions implements Serializable {

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
    @Column(name = "ngaytao")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date ngaytao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idQuestion")
    private Set<Answers> answersSet;
    @JoinColumn(name = "id_live", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Livestreams idLive;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User idUser;

    public Questions() {
    }

    public Questions(Integer id) {
        this.id = id;
    }

    public Questions(Integer id, String content, Date ngaytao) {
        this.id = id;
        this.content = content;
        this.ngaytao = ngaytao;
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

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    @JsonIgnore
    public Set<Answers> getAnswersSet() {
        return answersSet;
    }

    public void setAnswersSet(Set<Answers> answersSet) {
        this.answersSet = answersSet;
    }

    public Livestreams getIdLive() {
        return idLive;
    }

    public void setIdLive(Livestreams idLive) {
        this.idLive = idLive;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof Questions)) {
            return false;
        }
        Questions other = (Questions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dht.pojo.Questions[ id=" + id + " ]";
    }

}
