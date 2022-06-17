/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.medico.backend.resources;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import sistema.medico.backend.dao.MedicoDao;
import sistema.medico.backend.dao.Service;
import sistema.medico.backend.logic.Medico;
import sistema.medico.backend.logic.Paciente;
import sistema.medico.backend.logic.User;

@Path("/usuarios")
public class user {
    @Context
    HttpServletRequest request;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void registrarUser(User usuario) {
        try {
            Service.getInstance().registrarUser(usuario);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
