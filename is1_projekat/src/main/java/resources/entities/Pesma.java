/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "pesma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pesma.findAll", query = "SELECT p FROM Pesma p"),
    @NamedQuery(name = "Pesma.findByIdPesma", query = "SELECT p FROM Pesma p WHERE p.idPesma = :idPesma"),
    @NamedQuery(name = "Pesma.findByVideoId", query = "SELECT p FROM Pesma p WHERE p.videoId = :videoId"),
    @NamedQuery(name = "Pesma.findByTrajanje", query = "SELECT p FROM Pesma p WHERE p.trajanje = :trajanje")})
public class Pesma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "idPesma")
    private String idPesma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "videoId")
    private String videoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trajanje")
    private int trajanje;
    @ManyToMany(mappedBy = "pesmaList")
    private List<Korisnik> korisnikList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zvono")
    private List<Alarm> alarmList;

    public Pesma() {
    }

    public Pesma(String idPesma) {
        this.idPesma = idPesma;
    }

    public Pesma(String idPesma, String videoId, int trajanje) {
        this.idPesma = idPesma;
        this.videoId = videoId;
        this.trajanje = trajanje;
    }

    public String getIdPesma() {
        return idPesma;
    }

    public void setIdPesma(String idPesma) {
        this.idPesma = idPesma;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    @XmlTransient
    public List<Korisnik> getKorisnikList() {
        return korisnikList;
    }

    public void setKorisnikList(List<Korisnik> korisnikList) {
        this.korisnikList = korisnikList;
    }

    @XmlTransient
    public List<Alarm> getAlarmList() {
        return alarmList;
    }

    public void setAlarmList(List<Alarm> alarmList) {
        this.alarmList = alarmList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPesma != null ? idPesma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pesma)) {
            return false;
        }
        Pesma other = (Pesma) object;
        if ((this.idPesma == null && other.idPesma != null) || (this.idPesma != null && !this.idPesma.equals(other.idPesma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "resources.entities.Pesma[ idPesma=" + idPesma + " ]";
    }
    
}
