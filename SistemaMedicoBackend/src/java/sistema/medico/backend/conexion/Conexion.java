package sistema.medico.backend.conexion;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    private String USERNAME="root";
    private String PASSWORD="root";
    private String HOST="localhost";
    private String PORT="3306";
    private String DATABASE="sistemamed";
    private String CLASSNAME="com.mysql.cj.jdbc.Driver";
    private String URL="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE+"?useSSL=false";
    private static Connection conexion;
    private static Conexion instancia; // -> para guardar la coneccion en un obejto de tipo Conexion
    private Conexion() {
       
    }
   public Connection conectar(){
       try {
           Class.forName(CLASSNAME);
           conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           System.out.println("Conexion exitosa");
           return conexion;
       } catch (ClassNotFoundException | SQLException e) {
           JOptionPane.showMessageDialog(null, "Error: " + e);
       }
     return conexion;
   }
   
     public void cerrarConexion() throws SQLException{
        try{
         conexion.close();
        }catch(SQLException e){
         JOptionPane.showMessageDialog(null, "Error: "+e);
         conexion.close();
        }finally{
          conexion.close();
        }
    }

    public static Conexion getInstance(){
        if(instancia == null){
         instancia = new Conexion();
        }
         return instancia;
    }
     public Connection getConexion() {
        return conexion;
    }
}