/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.medico.backend.dao;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sistema.medico.backend.conexion.Conexion;
import sistema.medico.backend.logic.User;

/**
 *
 * @author PC
 */
public class UserDao {
   Conexion conectar = Conexion.getInstance();
   public Boolean registrar(User usuario) {
       Boolean bandera = false;
        try {
            Connection conexion = this.conectar.conectar();
            PreparedStatement insertar = conexion.prepareStatement("insert into User values(?,?,?)"); // actualiza, inserta, elimina en la base de datos
            insertar.setInt(1, usuario.getIdUser());
            insertar.setString(2, usuario.getPassword());
            insertar.setInt(3,(usuario.getRoll()));
            insertar.executeUpdate(); 
            bandera = true;
            this.conectar.cerrarConexion();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Object: "+ e);
            bandera = false;
        }
        return bandera;
    }
   
   // buscar usuario
   public User buscar(User u) {
        User usuario=null;
        try {
              Connection conexion = conectar.conectar();
              PreparedStatement buscar = conexion.prepareStatement("SELECT * FROM user WHERE idUser=? and password=?");
              buscar.setInt(1, u.getIdUser());
              buscar.setString(2, u.getPassword());
              ResultSet consulta = buscar.executeQuery();
              while(consulta.next()){
                usuario = new User();
                usuario.setIdUser(Integer.parseInt(consulta.getString("idUser")));
                usuario.setPassword(consulta.getString("password"));
                usuario.setRoll(Integer.parseInt(consulta.getString("roll")));
              }
              conectar.cerrarConexion();
        } catch (SQLException e) {
              JOptionPane.showMessageDialog(null,"Error: " + e);
        }
        return usuario;
    }
   // burcar si existe el usuario en la base de datos
   public Boolean buscaExiste(User usuario) {
        Boolean estado = false;
        Integer user = usuario.getIdUser();
        try {
              Connection conexion = conectar.conectar();
              PreparedStatement buscar = conexion.prepareStatement("SELECT idUser,password,roll FROM user WHERE idUser = ?");
              buscar.setInt(1, user);
              ResultSet consulta = buscar.executeQuery();
              while(consulta.next()){
                estado = true;
              }
              estado = false;
              conectar.cerrarConexion();
        } catch (SQLException e) {
              JOptionPane.showMessageDialog(null,"Error: " + e);
        }
        return estado;
    }
}
