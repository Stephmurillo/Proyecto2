package sistema.medico.backend.resources;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import sistema.medico.backend.dao.Service;
import sistema.medico.backend.logic.Alergia;


@Path("/alergias")
public class Alergias {
    /*
    POST URL http://localhost:8080/SistemaMedicoBackend/api/alergias
    {
        "idPaciente": "87654321",
        "nombre":"Nueces"
    }
     */
    @POST
    @Consumes(APPLICATION_JSON)
    public Boolean createAlergia(Alergia alergia) {
        try {
            return Service.getInstance().agregarAlergia(alergia);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    /*
    GET URL http://localhost:8080/SistemaMedicoBackend/api/alergias/87654321
    */
    @GET
    @Path("/{idPaciente}")
    @Produces(APPLICATION_JSON)
    public ArrayList<Alergia> readAlergiasByPaciente(@PathParam("idPaciente") String idPaciente) {
        try {
            return Service.getInstance().listarAlergiasPorPaciente(idPaciente);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    /*
    DELETE URL http://localhost:8080/SistemaMedicoBackend/api/alergias
    {
        "idPaciente": "87654321",
        "nombre":"Nueces"
    }
    */
    @DELETE
    @Path("/{idPaciente}/{name}")
    @Consumes(APPLICATION_JSON)
    public Boolean deleteAlergia(@PathParam("idPaciente") String idPaciente, @PathParam("name") String name) {
        try {
            return Service.getInstance().eliminarAlergia(idPaciente, name);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
