package sistema.medico.backend.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import sistema.medico.backend.dao.Service;
import sistema.medico.backend.logic.Paciente;

@Path("/pacientes")
public class Pacientes {

    /*
    POST URL http://localhost:8080/SistemaMedicoBackend/api/pacientes
    {
        "idPaciente": "2342332",
        "medico": { 
            "idUser": 1234
        },
        "infoContacto":"Numero telefono: 1234-1234",
        "nombre": "Denzel",
        "edad": 22
    }
     */
    @POST
    @Consumes(APPLICATION_JSON)
    public Boolean createPaciente(Paciente paciente) {
        try {
            return Service.getInstance().agregarPaciente(paciente);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    /*
    GET URL http://localhost:8080/SistemaMedicoBackend/api/pacientes/medico/1232
     */
    @GET
    @Path("/medico/{idMedico}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paciente> readPacientesPorMedico(@PathParam("idMedico") Integer idMedico){
        try {
            return Service.getInstance().listarPacientesPorMedico(idMedico);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    //------------------------------ALEXA--------------------------------------//
    @GET
    @Path("/medicos/{idMedico}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paciente> listarpacietes(@PathParam("idMedico") Integer idMedico){
        try {
            return Service.getInstance().listarPById(idMedico);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    //------------------------------ALEXA--------------------------------------//

    /*
    GET URL http://localhost:8080/SistemaMedicoBackend/api/pacientes/87654321
    */
    @GET
    @Path("/{idPaciente}")
    @Produces(APPLICATION_JSON)
    public Paciente readPacienteById(@PathParam("idPaciente") String idPaciente) {
        try {
            return Service.getInstance().obtenerPacientePorId(idPaciente);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    /*
    PUT URL http://localhost:8080/SistemaMedicoBackend/api/pacientes
    {
        "idPaciente": "87654321",
        "infoContacto":"Numero telefono: 4321-4321,
        "nombre": "Denzel",
        "edad": 23
    }
    */
    @PUT
    @Consumes(APPLICATION_JSON)
    public Boolean updatePaciente(Paciente paciente) {
        try {
            return Service.getInstance().editarPaciente(paciente);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
