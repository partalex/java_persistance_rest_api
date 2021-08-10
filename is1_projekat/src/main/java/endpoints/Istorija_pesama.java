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
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
        List<Pesma> pesma = em.createQuery("SELECT k FROM Pesma k WHERE k.videoId = :videoId", Pesma.class).setParameter("videoId", videoID).getResultList();
        if (pesma.isEmpty()) {
            return "Nepostojec videoId";
        }
        try {
            String chrome = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
            String arg = "youtube.com\\watch?v=" + videoID;

            ProcessBuilder pb = new ProcessBuilder(chrome, arg);
            Process process = pb.start();

            int trajanje;
//            int trajanje = pesma.get(0).getTrajanje() * 1000;
            trajanje = 2000;
            Thread.sleep(trajanje);
            process.destroy();
            if (process.isAlive()) {
                process.destroyForcibly();
                System.out.println("nije crkao");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("endpoints.Istorija_pesama.pustiPesmu()");
        }

        return videoID;
    }

}
