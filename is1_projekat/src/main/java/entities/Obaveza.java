/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SKT BABA ZOKA
 */
@Entity
@Table(name = "obaveza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Obaveza.findAll", query = "SELECT o FROM Obaveza o"),
    @NamedQuery(name = "Obaveza.findByIdObaveza", query = "SELECT o FROM Obaveza o WHERE o.idObaveza = :idObaveza"),
    @NamedQuery(name = "Obaveza.findByPocetak", query = "SELECT o FROM Obaveza o WHERE o.pocetak = :pocetak"),
    @NamedQuery(name = "Obaveza.findByTrajanje", query = "SELECT o FROM Obaveza o WHERE o.trajanje = :trajanje")})
public class Obaveza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "idObaveza")
    private String idObaveza;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pocetak")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pocetak;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trajanje")
    private int trajanje;
    @JoinTable(name = "praner_obaveza", joinColumns = {
        @JoinColumn(name = "obaveza", referencedColumnName = "idObaveza")}, inverseJoinColumns = {
        @JoinColumn(name = "planer", referencedColumnName = "idPlaner")})
    @ManyToMany
    private List<Planer> planerList;
    @JoinColumn(name = "destnacija", referencedColumnName = "idLokacija")
    @ManyToOne(optional = false)
    private Lokacija destnacija;
    @JoinColumn(name = "pocetna_lokacija", referencedColumnName = "idLokacija")
    @ManyToOne(optional = false)
    private Lokacija pocetnaLokacija;

    public Obaveza() {
    }

    public Obaveza(String idObaveza) {
        this.idObaveza = idObaveza;
    }

    public Obaveza(String idObaveza, Date pocetak, int trajanje) {
        this.idObaveza = idObaveza;
        this.pocetak = pocetak;
        this.trajanje = trajanje;
    }

    public String getIdObaveza() {
        return idObaveza;
    }

    public void setIdObaveza(String idObaveza) {
        this.idObaveza = idObaveza;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    @XmlTransient
    public List<Planer> getPlanerList() {
        return planerList;
    }

    public void setPlanerList(List<Planer> planerList) {
        this.planerList = planerList;
    }

    public Lokacija getDestnacija() {
        return destnacija;
    }

    public void setDestnacija(Lokacija destnacija) {
        this.destnacija = destnacija;
    }

    public Lokacija getPocetnaLokacija() {
        return pocetnaLokacija;
    }

    public void setPocetnaLokacija(Lokacija pocetnaLokacija) {
        this.pocetnaLokacija = pocetnaLokacija;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObaveza != null ? idObaveza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Obaveza)) {
            return false;
        }
        Obaveza other = (Obaveza) object;
        if ((this.idObaveza == null && other.idObaveza != null) || (this.idObaveza != null && !this.idObaveza.equals(other.idObaveza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Obaveza[ idObaveza=" + idObaveza + " ]";
    }
    
}
