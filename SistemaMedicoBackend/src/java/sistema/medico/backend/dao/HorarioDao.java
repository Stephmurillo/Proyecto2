package sistema.medico.backend.dao;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sistema.medico.backend.conexion.Conexion;
import sistema.medico.backend.logic.Horario;

public class HorarioDao {

    public HorarioDao(){}
    
    Conexion conectar = Conexion.getInstance();

    //============================== REGISTRAR HORARIOS ==============================
    public Boolean registrar(ArrayList<Horario> horarios) {
        Boolean bandera = false;
        try {
            Connection conexion = this.conectar.conectar();
            for (Horario horario : horarios) {
                PreparedStatement insertar = conexion.prepareStatement("insert into horario values(?,?,?,?)"); // actualiza, inserta, elimina en la base de datos
                insertar.setInt(1, horario.getMedicoId());
                insertar.setString(2, horario.getDia());
                insertar.setString(3, horario.getApertura());
                insertar.setString(4, horario.getCierre());
                insertar.executeUpdate();
            }
            this.conectar.cerrarConexion();
            bandera = true;
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Object: " + e);
            bandera = false;
        }
        return bandera;
    }

    //============================== BUSCAR HORARIOS ==============================
    public Horario buscar(Integer usuario) {
        Horario es = null;
        try {
            Connection conexion = conectar.conectar();
            PreparedStatement buscar = conexion.prepareStatement("SELECT * FROM horario WHERE medicoId=?");
            buscar.setInt(1, usuario);
            ResultSet consulta = buscar.executeQuery();
            while (consulta.next()) {
                es = new Horario();
                es.setMedicoId(Integer.parseInt(consulta.getString("medicoId")));
                es.setDia(consulta.getString("dia"));
                es.setApertura(consulta.getString("apertura"));
                es.setCierre(consulta.getString("cierre"));
            }
            conectar.cerrarConexion();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return es;
    }

    //============================== LISTAR HORARIOS ==============================
    public ArrayList<Horario> listarHorarios(Integer usuario) {
        ArrayList<Horario> horarios = new ArrayList<>();
        Horario es;
        try {
            Connection conexion = conectar.conectar();
            PreparedStatement buscar = conexion.prepareStatement("SELECT * FROM horario WHERE medicoId=?");
            buscar.setInt(1, usuario);
            ResultSet consulta = buscar.executeQuery();
            while (consulta.next()) {
                es = new Horario();
                es.setMedicoId(Integer.parseInt(consulta.getString("medicoId")));
                es.setDia(consulta.getString("dia"));
                es.setApertura(consulta.getString("apertura"));
                es.setCierre(consulta.getString("cierre"));
                horarios.add(es);
            }
            conectar.cerrarConexion();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return horarios;
    }
}
