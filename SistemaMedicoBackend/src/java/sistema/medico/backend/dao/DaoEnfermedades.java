/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.medico.backend.dao;


import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistema.medico.backend.conexion.Conexion;
import sistema.medico.backend.logic.Enfermedad;

public class DaoEnfermedades {

    Conexion conectar = Conexion.getInstance();
    
    // Obtener todos los enfermedades
    public ArrayList<Enfermedad> listarEnfermedadesPorPaciente(String idPaciente) {
        ArrayList<Enfermedad> enfermedades = new ArrayList<>();
        try {
            PreparedStatement statement = conectar.getConexion().prepareStatement("SELECT * FROM Enfermedades where id_paciente=?");
            statement.setString(1, idPaciente);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                enfermedades.add(mapEnfermedad(resultSet));
            }
            return enfermedades;
        } catch (SQLException e) {
            System.out.println("Error al buscar enfermedades: " + e.getMessage());
        } 
        return null; // Error
    }

    // Agregar enfermedad
    public Boolean agregarEnfermedad(Enfermedad enfermedad) {
        try {
            PreparedStatement statement = conectar.getConexion().prepareStatement("INSERT into Enfermedades(id_paciente, nombre) values (?,?)");
            statement.setString(1, enfermedad.getIdPaciente());
            statement.setString(2, enfermedad.getNombre());
            statement.executeUpdate();
            if(statement.getUpdateCount() > 0){
                return true;            
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar enfermedad: " + e.getMessage());
        }
        return false;
    }
    
    // Eliminar enfermedad
    public Boolean eliminarEnfermedad(String idPaciente, String name) {
        try {
            PreparedStatement statement = conectar.getConexion().prepareStatement("DELETE from Enfermedades where id_paciente=? and nombre=?");
            statement.setString(1, idPaciente);
            statement.setString(2, name);
            statement.executeUpdate();
            if(statement.getUpdateCount() > 0){
                return true;            
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar enfermedad: " + e.getMessage());
        }
        return false;
    }
    
    // Mapear la consulta a un Objeto Enfermedades
    public Enfermedad mapEnfermedad(ResultSet rs) throws SQLException {
        Enfermedad enfermedad = new Enfermedad();
        enfermedad.setIdPaciente(rs.getString("id_paciente"));
        enfermedad.setNombre(rs.getString("nombre"));
        return enfermedad;
    }
}
