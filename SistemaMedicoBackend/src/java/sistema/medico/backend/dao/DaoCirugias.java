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
import sistema.medico.backend.logic.Cirugia;


public class DaoCirugias {

    Conexion conectar = Conexion.getInstance();
    
    // Obtener todos las cirugias por paciente
    public ArrayList<Cirugia> listarCirugiasPorPaciente(String idPaciente) {
        ArrayList<Cirugia> enfermedades = new ArrayList<>();
        try {
            PreparedStatement statement = conectar.getConexion().prepareStatement("SELECT * FROM Cirugias where id_paciente=?");
            statement.setString(1, idPaciente);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                enfermedades.add(mapCirugia(resultSet));
            }
            return enfermedades;
        } catch (SQLException e) {
            System.out.println("Error al buscar cirugia: " + e.getMessage());
        } 
        return null; // Error
    }

    // Agregar cirugia
    public Boolean agregarCirugia(Cirugia enfermedad) {
        try {
            PreparedStatement statement = conectar.getConexion().prepareStatement("INSERT into cirugias(id_paciente, nombre) values (?,?)");
            statement.setString(1, enfermedad.getIdPaciente());
            statement.setString(2, enfermedad.getNombre());
            statement.executeUpdate();
            if(statement.getUpdateCount() > 0){
                return true;            
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar cirugia: " + e.getMessage());
        }
        return false;
    }
    
    // Eliminar enfermedad
    public Boolean eliminarCirugia(String idPaciente, String name) {
        try {
            PreparedStatement statement = conectar.getConexion().prepareStatement("DELETE from cirugias where id_paciente=? and nombre=?");
            statement.setString(1, idPaciente);
            statement.setString(2, name);
            statement.executeUpdate();
            if(statement.getUpdateCount() > 0){
                return true;            
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar cirugia: " + e.getMessage());
        }
        return false;
    }
    
    // Mapear la consulta a un Objeto Cirugias
    public Cirugia mapCirugia(ResultSet rs) throws SQLException {
        Cirugia cirugia = new Cirugia();
        cirugia.setIdPaciente(rs.getString("id_paciente"));
        cirugia.setNombre(rs.getString("nombre"));
        return cirugia;
    }
}
