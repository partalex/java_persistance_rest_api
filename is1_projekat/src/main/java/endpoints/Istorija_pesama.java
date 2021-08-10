/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import filters.BasicAuthFilter;
import java.util.List;
import javax.ws.rs.PathParam;
import resources.entities.Korisnik;
import resources.entities.Pesma;

/**
 *
 * @author SKT BABA ZOKA
 */
@Path("pesme")
@Stateless
public class Istorija_pesama {

    @PersistenceContext(unitName = "my_per")
    EntityManager em;

    @GET
    public List<Pesma> get() {
        Korisnik ulogovan = BasicAuthFilter.ulogovan();
        List<Pesma> resultList = ulogovan.getPesmaList();
        return resultList;
    }
    
    @Path("{videoID}")
    @GET
    public String pustiPesmu(@PathParam("videoID") String videoID){
        return videoID;
    }

}
