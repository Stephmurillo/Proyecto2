package sistema.medico.backend.dao;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sistema.medico.backend.conexion.Conexion;
import sistema.medico.backend.logic.Medico;

public class MedicoDao {
    Conexion conectar = Conexion.getInstance();
    HorarioDao horario = new HorarioDao();
    public MedicoDao(){
    }
    
    public Boolean registrar(Medico med) {
        Boolean bandera = false;
        try {
            Connection conexion = this.conectar.conectar();
            PreparedStatement insertar = conexion.prepareStatement("insert into medico values(?,?,?,?,?,?,?,?,?)");
            insertar.setInt(1, med.getIdUser());
            insertar.setFloat(2, Float.parseFloat(med.getCosto()));
            insertar.setString(3, med.getNombre());
            insertar.setString(4, String.valueOf(med.getEstado()));
            insertar.setString(5, med.getDescripcion());
            insertar.setString(6, med.getDireccion());
            insertar.setString(7, med.getLocalidad());
            insertar.setString(8, med.getEspecialidad());
            insertar.setInt(9, med.getTiempoCitas());
            insertar.executeUpdate();
            this.conectar.cerrarConexion();
            bandera = true;
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Object: " + e);
            bandera = false;
        }
        return bandera;
    }
    
    public Medico buscaMedico(Integer id) {
        Medico auxi = null;
        try {
            Connection conexion = conectar.conectar();
            PreparedStatement buscar = conexion.prepareStatement("SELECT * FROM medico WHERE idUser = ?");
            buscar.setInt(1, id);
            ResultSet consulta = buscar.executeQuery();
            while (consulta.next()) {
                auxi = new Medico();
                auxi.setIdUser(Integer.parseInt(consulta.getString("idUser")));
                auxi.setCosto(consulta.getString("costo"));
                auxi.setNombre(consulta.getString("nombre"));
                auxi.setEstado(Integer.parseInt(consulta.getString("estado")));
                auxi.setDescripcion(consulta.getString("decripcion"));
                auxi.setDireccion(consulta.getString("direccion"));
                auxi.setLocalidad(consulta.getString("localidad"));
                auxi.setEspecialidad(consulta.getString("especialidad"));
                auxi.setTiempoCitas(consulta.getInt("tiempoCita"));
                auxi.setListahorarios(horario.listarHorarios(auxi.getIdUser()));
            }
                conectar.cerrarConexion();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return auxi;
    }
   
//    // hay que tomar en cuenta la tabla de horarios y pagos....
//    public ArrayList<Medico> buscarMedicos(String med) {
//        Medico auxi ;
//        ArrayList<Medico> lista = new ArrayList<>();
//        try {
//            Connection conexion = conectar.conectar();
//            PreparedStatement buscar = conexion.prepareStatement("SELECT * FROM medico WHERE User_idUser = ?");
//            buscar.setString(1, med);
//            ResultSet consulta = buscar.executeQuery();
//            while (consulta.next()) {
//                auxi = new Medico();
//                auxi.setCosto(Double.parseDouble(consulta.getString("costo")));
//                auxi.setIdentification(consulta.getString("User_idUser"));
//                auxi.setName(consulta.getString("nombre"));
//                auxi.setEspecialidades(es.listarEspecialidades(med));
//                auxi.setLocalidades(l.listarLocalidades(med));
//                auxi.setEstado(consulta.getString("estado"));
//                lista.add(auxi);
//            }
//                conectar.cerrarConexion();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e);
//        }
//        return lista;
//    }
    
//    public ArrayList<Medico> buscarTodosLosMedicos() {
//        Medico auxi ;
//        ArrayList<Medico> lista = new ArrayList<>();
//        try {
//            Connection conexion = conectar.conectar();
//            PreparedStatement buscar = conexion.prepareStatement("SELECT * FROM medico");
//            ResultSet consulta = buscar.executeQuery();
//            while (consulta.next()) {
//                auxi = new Medico();
//                auxi.setCosto(Double.parseDouble(consulta.getString("costo")));
//                auxi.setIdentification(consulta.getString("User_idUser"));
//                auxi.setName(consulta.getString("nombre"));
//                auxi.setEstado(consulta.getString("estado"));
//                lista.add(auxi);
//            }
//                conectar.cerrarConexion();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e);
//        }
//        return lista;
//    }
    
    //por ciudad y espe...
//    public ArrayList<Medico> listarMedicos(String ciudad,String especialidad) {
//          ArrayList<Medico> medicos = new ArrayList<>();
//          Medico medico;
//          String estado = "Aprobado";
//          localidadesDao localidades = new localidadesDao();
//          especialidadesDao especialidades = new especialidadesDao();
//        try {
//            Connection conexion = conectar.conectar();
//            PreparedStatement buscar = conexion.prepareStatement("SELECT idUser,nombre,costo,estado,l.ciudad,e.Especialidad FROM medico m JOIN localidades l ON m.User_idUser=l.Medico_id JOIN especialidades e ON m.User_idUser = e.Medico_id WHERE l.ciudad=? and e.Especialidad=? and m.estado=?");
//            buscar.setString(1, ciudad.trim());
//            buscar.setString(2, especialidad.trim());
//            buscar.setString(3, estado.trim());
//            ResultSet consulta = buscar.executeQuery();
//            System.out.println(consulta);
//            while (consulta.next()) {
//                medico = new Medico();
//                medico.setIdentification(consulta.getString("User_idUser"));
//                medico.setName(consulta.getString("nombre"));
//                medico.setCosto(Double.parseDouble(consulta.getString("costo")));
//                medico.setEstado(consulta.getString("estado"));
//                String ciu = consulta.getString("ciudad");
//                String esp = consulta.getString("Especialidad");
//                medico.setEspecialidades(especialidades.listarEspecialidPorNombre(medico.getIdentification(), esp));
//                medico.setLocalidades(localidades.listarLocalidadesPorCiudad(medico.getIdentification(), ciu));
//                medicos.add(medico);
//            }
//             conectar.cerrarConexion();
//        } catch (SQLException e) {
//            System.out.println("Error:" +e);
//        }
//        return medicos;
//    }
//    public void modificar(Medico med) {
//        try {
//            Connection conexion = conectar.conectar();
//            PreparedStatement modificar = conexion.prepareStatement("UPDATE medico SET estado = ? WHERE idUser=?");// aqui es para que capture el id que estamos buscando
//            modificar.setString(1, med.getEstado());
//            modificar.setInt(2, Integer.parseInt(med.getIdentification()));
//            modificar.executeUpdate(); // actualiza la lista
//            conectar.cerrarConexion();
//        } catch (HeadlessException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e);
//        }
//    }
//    
//    public void modificarCosto(Medico med, String iden) {
//        try {
//            Connection conexion = conectar.conectar();
//            PreparedStatement modificar = conexion.prepareStatement("UPDATE medico SET costo=? WHERE idUser=?");// aqui es para que capture el id que estamos buscando
//            modificar.setDouble(1, med.getCosto());
//            modificar.setInt(2, Integer.parseInt(iden));
//            modificar.executeUpdate(); // actualiza la lista
//            
//        } catch (HeadlessException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e);
//        }
//    }
    
//============================== LISTAR LOCALIDADES ==================================
    public ArrayList<String> listarLocalidades() {
        ArrayList<String> localidades = new ArrayList<>();
        String localidad;
        try {
            Connection conexion = conectar.conectar();
            PreparedStatement buscar = conexion.prepareStatement("SELECT * FROM Localidades");
            ResultSet consulta = buscar.executeQuery();
            while (consulta.next()) {
                localidad = consulta.getString("nombre");
                localidades.add(localidad);
            }
            conectar.cerrarConexion();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return localidades;
    }
    
//============================== LISTAR ESPECIALIDADES ==================================
    public ArrayList<String> listarEspecialidades() {
        ArrayList<String> especialidades = new ArrayList<>();
        String especialidad;
        try {
            Connection conexion = conectar.conectar();
            PreparedStatement buscar = conexion.prepareStatement("SELECT * FROM especialidades");
            ResultSet consulta = buscar.executeQuery();
            while (consulta.next()) {
                especialidad = new String();
                especialidad = consulta.getString("nombre");
                especialidades.add(especialidad);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return especialidades;
    }
}