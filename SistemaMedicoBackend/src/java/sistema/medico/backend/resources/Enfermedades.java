package sistema.medico.backend.resources;
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
import sistema.medico.backend.logic.Enfermedad;

@Path("/enfermedades")
public class Enfermedades {
    /*
    POST URL http://localhost:8080/SistemaMedicoBackend/api/enfermedades
    {
        "idPaciente": "87654321",
        "nombre":"Meningitis"
    }
     */
    @POST
    @Consumes(APPLICATION_JSON)
    public Boolean createEnfermedad(Enfermedad enfermedad) {
        try {
            return Service.getInstance().agregarEnfermedad(enfermedad);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    /*
    GET URL http://localhost:8080/SistemaMedicoBackend/api/enfermedades/87654321
    */
    @GET
    @Path("/{idPaciente}")
    @Produces(APPLICATION_JSON)
    public List<Enfermedad> readEnfermedadesByPaciente(@PathParam("idPaciente") String idPaciente) {
        try {
            return Service.getInstance().listarEnfermedadesPorPaciente(idPaciente);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    /*
    DELETE URL http://localhost:8080/SistemaMedicoBackend/api/enfermedades
    {
        "idPaciente": "87654321",
        "nombre":"Meningitis"
    }
    */
    @DELETE
    @Path("/{idPaciente}/{name}")
    @Consumes(APPLICATION_JSON)
    public Boolean deleteEnfermedad(@PathParam("idPaciente") String idPaciente, @PathParam("name") String name) {
        try {
            return Service.getInstance().eliminarEnfermedad(idPaciente, name);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
