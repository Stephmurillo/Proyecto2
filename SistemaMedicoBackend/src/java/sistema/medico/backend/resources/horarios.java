/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.medico.backend.resources;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import sistema.medico.backend.dao.Service;
import sistema.medico.backend.logic.Horario;

@Path("/horarios")
public class horarios {
    @Context
    HttpServletRequest request;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void registrarUser(ArrayList<Horario> lista) {
        try {
            Service.getInstance().agregarHorarios(lista);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
 }
