package filters;

import resources.entities.Korisnik;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

@Provider
public class BasicAuthFilter implements ContainerRequestFilter {

    @PersistenceContext(unitName = "my_per")
    EntityManager em;
    private static Korisnik ulogovan;

    public static Korisnik ulogovan() {
        return BasicAuthFilter.ulogovan;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> authHeaderValues = requestContext.getHeaders().get("Authorization");

        if (authHeaderValues != null && authHeaderValues.size() > 0) {
            String authHeaderValue = authHeaderValues.get(0);
            String decodedAuthHeaderValue = new String(Base64.getDecoder().decode(authHeaderValue.replaceFirst("Basic ", "")), StandardCharsets.UTF_8);
            StringTokenizer stringTokenizer = new StringTokenizer(decodedAuthHeaderValue, ":");
            String username = stringTokenizer.nextToken();
            String password = stringTokenizer.nextToken();

            List<Korisnik> users = em.createNamedQuery("Korisnik.findByIdKorisnik", Korisnik.class).setParameter("idKorisnik", username).getResultList();

            if (users.size() != 1) {
                Response response = Response.status(Response.Status.UNAUTHORIZED).entity("Korisnicko ime ili sifra nije ispravno.").build();
                requestContext.abortWith(response);
                return;
            }
            BasicAuthFilter.ulogovan = null;

            Korisnik user = users.get(0);

            if (!user.getSifra().equals(password)) {
                Response response = Response.status(Response.Status.UNAUTHORIZED).entity("Korisnicko ime ili sifra nije ispravno.").build();
                requestContext.abortWith(response);
                return;
            }

            UriInfo uriInfo = requestContext.getUriInfo();
            List<PathSegment> pathSegments = uriInfo.getPathSegments();
            if (pathSegments.size() > 1) {
                String pathSegment1 = pathSegments.get(1).getPath();
            }
            BasicAuthFilter.ulogovan = user;
            return;
        }

        Response response = Response.status(Response.Status.UNAUTHORIZED).entity("Posaljite kredencijale.").build();
        requestContext.abortWith(response);
        return;
    }

}
