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
import sistema.medico.backend.logic.Cirugia;

@Path("/cirugias")
public class Cirugias {
    /*
    POST URL http://localhost:8080/SistemaMedicoBackend/api/cirugias
    {
        "idPaciente": "87654321",
        "nombre":"Laparotomia exploradora"
    }
     */
    @POST
    @Consumes(APPLICATION_JSON)
    public Boolean createCirugia(Cirugia cirugia) {
        try {
            return Service.getInstance().agregarCirugia(cirugia);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    /*
    GET URL http://localhost:8080/SistemaMedicoBackend/api/cirugias/87654321
    */
    @GET
    @Path("/{idPaciente}")
    @Produces(APPLICATION_JSON)
    public List<Cirugia> readCirugiaesByPaciente(@PathParam("idPaciente") String idPaciente) {
        try {
            return Service.getInstance().listarCirugiasPorPaciente(idPaciente);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    /*
    DELETE URL http://localhost:8080/SistemaMedicoBackend/api/pacientes
    {
        "idPaciente": "87654321",
        "nombre":"Laparotomia exploradora"
    }
    */
    @DELETE
    @Path("/{idPaciente}/{name}")
    @Consumes(APPLICATION_JSON)
    public Boolean deleteCirugia(@PathParam("idPaciente") String idPaciente, @PathParam("name") String name) {
        try {
            return Service.getInstance().eliminarCirugia(idPaciente, name);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
