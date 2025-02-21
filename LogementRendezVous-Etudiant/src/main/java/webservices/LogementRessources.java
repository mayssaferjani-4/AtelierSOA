package webservices;

import entities.Logement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/logement")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Logement API", description = "Endpoints pour gérer les logements")
public class LogementRessources {
    private static LogementBusiness help = new LogementBusiness();

    @GET
    @Path("/getAll")
    @Operation(summary = "Récupérer tous les logements", description = "Retourne la liste des logements disponibles.")
    public Response getAll() {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(help.getLogements())
                .build();
    }

    @POST
    @Path("/add")
    @Operation(summary = "Ajouter un logement", description = "Ajoute un nouveau logement dans la base de données.")
    public Response addLogement(Logement logement) {
        if (help.addLogement(logement)) {
            return Response.status(200)
                    .entity(logement)
                    .build();
        }
        return Response.status(400)
                .entity("Échec de l'ajout du logement")
                .build();
    }

    @PUT
    @Path("/update/{reference}")
    @Operation(summary = "Mettre à jour un logement", description = "Met à jour un logement existant avec la référence donnée.")
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        if (help.updateLogement(reference, logement)) {
            return Response.ok("Logement mis à jour avec succès").build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Logement avec référence " + reference + " non trouvé.")
                .build();
    }

    @DELETE
    @Path("/delete/{reference}")
    @Operation(summary = "Supprimer un logement", description = "Supprime un logement en fonction de sa référence.")
    public Response deleteLogement(@PathParam("reference") int reference) {
        if (help.deleteLogement(reference)) {
            return Response.ok("Logement supprimé avec succès").build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Logement avec référence " + reference + " non trouvé.")
                .build();
    }
}
