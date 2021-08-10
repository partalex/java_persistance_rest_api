/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SKT BABA ZOKA
 */
@Entity
@Table(name = "planer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planer.findAll", query = "SELECT p FROM Planer p"),
    @NamedQuery(name = "Planer.findByIdPlaner", query = "SELECT p FROM Planer p WHERE p.idPlaner = :idPlaner")})
public class Planer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPlaner")
    private Integer idPlaner;
    @ManyToMany(mappedBy = "planerList")
    private List<Obaveza> obavezaList;
    @JoinColumn(name = "podsetnik", referencedColumnName = "idAlarm")
    @ManyToOne
    private Alarm podsetnik;
    @OneToMany(mappedBy = "planer")
    private List<Korisnik> korisnikList;

    public Planer() {
    }

    public Planer(Integer idPlaner) {
        this.idPlaner = idPlaner;
    }

    public Integer getIdPlaner() {
        return idPlaner;
    }

    public void setIdPlaner(Integer idPlaner) {
        this.idPlaner = idPlaner;
    }

    @XmlTransient
    public List<Obaveza> getObavezaList() {
        return obavezaList;
    }

    public void setObavezaList(List<Obaveza> obavezaList) {
        this.obavezaList = obavezaList;
    }

    public Alarm getPodsetnik() {
        return podsetnik;
    }

    public void setPodsetnik(Alarm podsetnik) {
        this.podsetnik = podsetnik;
    }

    @XmlTransient
    public List<Korisnik> getKorisnikList() {
        return korisnikList;
    }

    public void setKorisnikList(List<Korisnik> korisnikList) {
        this.korisnikList = korisnikList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlaner != null ? idPlaner.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planer)) {
            return false;
        }
        Planer other = (Planer) object;
        if ((this.idPlaner == null && other.idPlaner != null) || (this.idPlaner != null && !this.idPlaner.equals(other.idPlaner))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "resources.entities.Planer[ idPlaner=" + idPlaner + " ]";
    }
    
}
