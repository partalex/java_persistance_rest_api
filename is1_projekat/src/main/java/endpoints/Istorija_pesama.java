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
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public String pustiPesmu(@PathParam("videoID") String videoID) throws InterruptedException {
        try {

            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        ProcessBuilder pb
                                = new ProcessBuilder(
                                        "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe",
                                        "youtube.com\\watch?v=hPQGj6G0mD8&t");

                        pb.start();

                        System.out.println("Google Chrome launched!");
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            };
            thread.start();
            thread.join(4000);
        } catch (InterruptedException e) {
            System.out.println("endpoints.Istorija_pesama.pustiPesmu()");
        }

        return videoID;
    }

}
