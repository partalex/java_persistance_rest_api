/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.rmi.server.ObjID;
import java.util.Date;

/**
 *
 * @author SKT BABA ZOKA
 */
public class Alarm {

    public Alarm(Object zvono, Date vreme, int ponaljanje) {
        this.zvono = zvono;
        this.vreme = vreme;
        this.ponaljanje = ponaljanje;
    }

    /*
    promeniti tip
     */
    private Object zvono;
    private Date vreme;
    private int ponaljanje;

    /*
        **navij alarm u zadatom trenutku
     */
    void navij_alarm(Date date) {
    }

    /*
        ** navija periodini alarm za zadate parametre
     */
    void navij_periodicno_alarm() {

    }

    /*
        **prmeniti tip zvona
        **postavlja zvono za alarm
     */
    void set_Zvono(Object zvono) {
        this.zvono = zvono;
    }

}
