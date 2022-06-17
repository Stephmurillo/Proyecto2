package sistema.medico.backend.resources;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import sistema.medico.backend.dao.MedicoDao;
import sistema.medico.backend.dao.Service;
import sistema.medico.backend.logic.Medico;

@Path("/medicos")
public class RestMedico {

    MedicoDao med = new MedicoDao();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public void registrarMedico(Medico medico) {
        try {
            Service.getInstance().agregarMedico(medico);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("localidades")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<String> AllLocalidades() {
        try {
            ArrayList<String> localidades = med.listarLocalidades();
            return localidades;
        } catch (Exception e) {
            return null;
        }
    }

    @GET
    @Path("especialidades")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<String> AllEspecialidades() {
        try {
            ArrayList<String> especialidades = med.listarEspecialidades();
            return especialidades;
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    //---------------------------------------------------------------------------
    @GET
    @Path("/{idMedico}")
    @Produces(APPLICATION_JSON)
    public Medico buscarMedico(@PathParam("idMedico") Integer idMedico) {
        try {
            return Service.getInstance().buscarMedico(idMedico);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
