/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.medico.backend.resources;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import sistema.medico.backend.dao.Service;
import sistema.medico.backend.logic.Medico;
import sistema.medico.backend.logic.User;

@Path("/login")
@PermitAll
public class login {
    @Context
    HttpServletRequest request;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public User login(User usuario) {  
            User logged = null;
            try {
                logged= Service.getInstance().buscarUser(usuario);
                if(!logged.getPassword().equals(usuario.getPassword())) throw new Exception("Clave incorrecta");
                request.getSession(true).setAttribute("user", logged);
                return logged;
            } catch (Exception ex) {
                throw new NotFoundException();
            }  
    }
    @DELETE 
    public void logout() {  
        HttpSession session = request.getSession(true);
        session.removeAttribute("user");           
        session.invalidate();
    }
    /*@GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Medico returnMedico(@PathParam("id") Integer id) throws Exception { 
        return Service.getInstance().buscarMedico(id);
    }*/ 
}

