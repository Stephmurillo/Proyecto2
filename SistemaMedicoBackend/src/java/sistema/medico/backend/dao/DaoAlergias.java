/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.medico.backend.dao;


import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistema.medico.backend.conexion.Conexion;
import sistema.medico.backend.logic.Alergia;


public class DaoAlergias {

    Conexion conectar = Conexion.getInstance();
    
    // Obtener todos los alergias por paciente
    public ArrayList<Alergia> listarAlergiasPorPaciente(String idPaciente) {
        ArrayList<Alergia> alergiaes = new ArrayList<>();
        try {
            PreparedStatement statement = conectar.getConexion().prepareStatement("SELECT * FROM alergias where id_paciente=?");
            statement.setString(1, idPaciente);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                alergiaes.add(mapAlergia(resultSet));
            }
            return alergiaes;
        } catch (SQLException e) {
            System.out.println("Error al buscar alergias: " + e.getMessage());
        } 
        return null; // Error
    }

    // Agregar alergia
    public Boolean agregarAlergia(Alergia alergia) {
        try {
            PreparedStatement statement = conectar.getConexion().prepareStatement("INSERT into alergias(id_paciente, nombre) values (?,?)");
            statement.setString(1, alergia.getIdPaciente());
            statement.setString(2, alergia.getNombre());
            statement.executeUpdate();
            if(statement.getUpdateCount() > 0){
                return true;            
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar alergia: " + e.getMessage());
        }
        return false;
    }
    
    // Eliminar alergia
    public Boolean eliminarAlergia(String idPaciente, String name) {
        try {
            PreparedStatement statement = conectar.getConexion().prepareStatement("DELETE from alergias where id_paciente=? and nombre=?");
            statement.setString(1, idPaciente);
            statement.setString(2, name);
            statement.executeUpdate();
            if(statement.getUpdateCount() > 0){
                return true;            
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar alergia: " + e.getMessage());
        }
        return false;
    }
    
    // Mapear la consulta a un Objeto Alergias
    public Alergia mapAlergia(ResultSet rs) throws SQLException {
        Alergia alergia = new Alergia();
        alergia.setIdPaciente(rs.getString("id_paciente"));
        alergia.setNombre(rs.getString("nombre"));
        return alergia;
    }
}
