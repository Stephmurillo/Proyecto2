/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.medico.backend.dao;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sistema.medico.backend.conexion.Conexion;
import sistema.medico.backend.logic.Cita;
import sistema.medico.backend.logic.User;

/**
 *
 * @author PC
 */
public class citasDao {
    Conexion conectar = Conexion.getInstance();
    UserDao a = new UserDao();
    User aux;
    
    public void registrar(Cita usuario) {
        try {
            Connection conexion = this.conectar.conectar();
            PreparedStatement insertar = conexion.prepareStatement("insert into cita values(?,?,?,?,?,?)"); // actualiza, inserta, elimina en la base de datos
            insertar.setString(1, usuario.getCodigo());
            insertar.setString(2, usuario.getEstado());
            //insertar.setInt(3, Integer.parseInt(usuario.getPaciente().getIdentification()));
          //  insertar.setInt(4, Integer.parseInt(usuario.getMedico().getIdentification()));
            insertar.setString(5, String.valueOf(usuario.getFecha()));
           // insertar.setString(6, usuario.getDescripcion());
            insertar.executeUpdate(); 
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Object: "+ e);
        }
    }
}
