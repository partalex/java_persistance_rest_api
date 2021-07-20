/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SKT BABA ZOKA
 */
@Entity
@Table(name = "lokacija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lokacija.findAll", query = "SELECT l FROM Lokacija l"),
    @NamedQuery(name = "Lokacija.findByIdLokacija", query = "SELECT l FROM Lokacija l WHERE l.idLokacija = :idLokacija")})
public class Lokacija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "idLokacija")
    private String idLokacija;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destnacija")
    private List<Obaveza> obavezaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pocetnaLokacija")
    private List<Obaveza> obavezaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kuca")
    private List<Korisnik> korisnikList;

    public Lokacija() {
    }

    public Lokacija(String idLokacija) {
        this.idLokacija = idLokacija;
    }

    public String getIdLokacija() {
        return idLokacija;
    }

    public void setIdLokacija(String idLokacija) {
        this.idLokacija = idLokacija;
    }

    @XmlTransient
    public List<Obaveza> getObavezaList() {
        return obavezaList;
    }

    public void setObavezaList(List<Obaveza> obavezaList) {
        this.obavezaList = obavezaList;
    }

    @XmlTransient
    public List<Obaveza> getObavezaList1() {
        return obavezaList1;
    }

    public void setObavezaList1(List<Obaveza> obavezaList1) {
        this.obavezaList1 = obavezaList1;
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
        hash += (idLokacija != null ? idLokacija.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lokacija)) {
            return false;
        }
        Lokacija other = (Lokacija) object;
        if ((this.idLokacija == null && other.idLokacija != null) || (this.idLokacija != null && !this.idLokacija.equals(other.idLokacija))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Lokacija[ idLokacija=" + idLokacija + " ]";
    }
    
}
