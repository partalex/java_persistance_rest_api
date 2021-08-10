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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "korisnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k"),
    @NamedQuery(name = "Korisnik.findByIdKorisnik", query = "SELECT k FROM Korisnik k WHERE k.idKorisnik = :idKorisnik"),
    @NamedQuery(name = "Korisnik.findBySifra", query = "SELECT k FROM Korisnik k WHERE k.sifra = :sifra")})
public class Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "idKorisnik")
    private String idKorisnik;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sifra")
    private String sifra;

    @JoinTable(name = "korisnik_pesma", joinColumns = {
        @JoinColumn(name = "korisnik", referencedColumnName = "idKorisnik")}, inverseJoinColumns = {
        @JoinColumn(name = "pesma", referencedColumnName = "idPesma")})
    @ManyToMany
    private List<Pesma> pesmaList;
    
    @JoinColumn(name = "kuca", referencedColumnName = "idLokacija")
    @ManyToOne(optional = false)
    private Lokacija kuca;
    @JoinColumn(name = "planer", referencedColumnName = "idPlaner")
    @ManyToOne
    private Planer planer;

    public Korisnik() {
    }

    public Korisnik(String idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public Korisnik(String idKorisnik, String sifra) {
        this.idKorisnik = idKorisnik;
        this.sifra = sifra;
    }

    public String getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(String idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @XmlTransient
    public List<Pesma> getPesmaList() {
        return pesmaList;
    }

    public void setPesmaList(List<Pesma> pesmaList) {
        this.pesmaList = pesmaList;
    }

    public Lokacija getKuca() {
        return kuca;
    }

    public void setKuca(Lokacija kuca) {
        this.kuca = kuca;
    }

    public Planer getPlaner() {
        return planer;
    }

    public void setPlaner(Planer planer) {
        this.planer = planer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKorisnik != null ? idKorisnik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.idKorisnik == null && other.idKorisnik != null) || (this.idKorisnik != null && !this.idKorisnik.equals(other.idKorisnik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "resources.entities.Korisnik[ idKorisnik=" + idKorisnik + " ]";
    }

}
