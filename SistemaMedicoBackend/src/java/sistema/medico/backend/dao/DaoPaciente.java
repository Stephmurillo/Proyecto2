/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.medico.backend.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sistema.medico.backend.conexion.Conexion;
import sistema.medico.backend.logic.Medico;
import sistema.medico.backend.logic.Paciente;

public class DaoPaciente {

    Conexion conectar = Conexion.getInstance();

    // Obtener todos los pacientes
    public ArrayList<Paciente> listarPacientesPorMedico(Integer idMedico) {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        try {
            PreparedStatement statement = conectar.getConexion().prepareStatement("SELECT * from paciente where idMedico=?");
            statement.setString(1, idMedico.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pacientes.add(mapPaciente(resultSet));
            }
            return pacientes;
        } catch (SQLException e) {
            System.out.println("Error al buscar pacientes del doctor " + idMedico.toString() + ": " + e.getMessage());
        }
        return null; // Error
    }

    //--------------------------------lista pacientes Alexa-----------------------------------------------//
    public ArrayList<Paciente> listarpacientes(Integer usuario) {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        Paciente es;
        try {
            Connection conexion = conectar.conectar();
            PreparedStatement buscar = conexion.prepareStatement("SELECT * from paciente where idMedico=?");
            buscar.setInt(1, usuario);
            ResultSet consulta = buscar.executeQuery();
            while (consulta.next()) {
                es = new Paciente();
                es.setIdPaciente(consulta.getString("idPaciente"));
                es.setInfoContacto(consulta.getString("infoContacto"));
                es.getMedico().setIdUser(Integer.parseInt(consulta.getString("idMedico")));
                es.setNombre(consulta.getString("nombre"));
                es.setEdad(Integer.parseInt(consulta.getString("edad")));
                pacientes.add(es);
            }
            conectar.cerrarConexion();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return pacientes;

    }

    // Obtener paciente por id
    public Paciente obtenerPacientePorId(String idPaciente) {
        try {
            PreparedStatement statement = conectar.getConexion().prepareStatement("SELECT * FROM paciente where idPaciente=?");
            statement.setString(1, idPaciente);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapPaciente(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar paciente con el id " + idPaciente + ": " + e.getMessage());
        }
        return null; // Error
    }

    // Editar paciente por id
    public Boolean editarPaciente(Paciente paciente) {
        try {
            PreparedStatement statement = conectar.getConexion().prepareStatement("UPDATE paciente set infoContacto=?, nombre=?, edad=? where idPaciente=?");
            statement.setString(1, paciente.getInfoContacto());
            statement.setString(2, paciente.getNombre());
            statement.setString(3, String.valueOf(paciente.getEdad()));
            statement.setString(4, paciente.getIdPaciente());
            statement.executeUpdate();
            if (statement.getUpdateCount() == 0) {
                throw new Exception("No se actualizo ninguna fila");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error al editar paciente con el id " + paciente.getIdPaciente() + ": " + e.getMessage());
        }
        return false;
    }

    // Agregar paciente 
    public Boolean agregarPaciente(Paciente paciente) {
        try {
            PreparedStatement statement = conectar.getConexion().prepareStatement("INSERT into paciente(idPaciente, idMedico, infoContacto, nombre, edad) values (?,?,?,?,?)");
            statement.setString(1, paciente.getIdPaciente());
            statement.setString(2, paciente.getMedico().getIdUser().toString());
            statement.setString(3, paciente.getInfoContacto());
            statement.setString(4, paciente.getNombre());
            statement.setString(5, String.valueOf(paciente.getEdad()));
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar paciente: " + e.getMessage());
        }
        return false;
    }

    // Mapear la consulta a un Objeto Paciente
    public Paciente mapPaciente(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(rs.getString("idPaciente"));
        Medico medico = new Medico();
        medico.setIdUser(rs.getInt("idMedico"));
        paciente.setMedico(medico);
        paciente.setInfoContacto(rs.getString("infoContacto"));
        paciente.setNombre(rs.getString("nombre"));
        paciente.setEdad(rs.getInt("edad"));
        return paciente;
    }
}
