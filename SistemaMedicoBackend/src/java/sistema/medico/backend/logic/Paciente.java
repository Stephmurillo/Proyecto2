/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.medico.backend.logic;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author PC
 */
public class Paciente {
     String idPaciente;
    String infoContacto;//me permite modificar datos que no sean el ID
    String nombre;
    Integer edad;
    Medico medico;
    ArrayList<Alergia> listalergias;//me permitiera añadir alergias, cirugias, enfermedades, etc
    ArrayList<Cirugia> listacirugias;
    ArrayList<Enfermedad> listaenfermedades;

    public Paciente(String idPaciente, String infoContacto, String nombre, Integer edad,Medico medico, ArrayList<Alergia> listalergias, ArrayList<Cirugia> listacirugias, ArrayList<Enfermedad> listaenfermedades) {
        this.idPaciente = idPaciente;
        this.infoContacto = infoContacto;
        this.nombre = nombre;
        this.edad = edad;
        this.medico = medico;
        this.listalergias = listalergias;
        this.listacirugias = listacirugias;
        this.listaenfermedades = listaenfermedades;
    }

    public Paciente(String idPaciente, String infoContacto, String nombre, Integer edad) {
        this.idPaciente = idPaciente;
        this.infoContacto = infoContacto;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Paciente() {
        this.edad= 0;
        this.idPaciente = "";
        this.infoContacto ="";
        this.listacirugias = new ArrayList<>();
        this.listaenfermedades = new ArrayList<>();
        this.listalergias = new ArrayList<>();
        this.nombre = "";
        medico = new Medico();
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getInfoContacto() {
        return infoContacto;
    }

    public void setInfoContacto(String infoContacto) {
        this.infoContacto = infoContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public ArrayList<Alergia> getListalergias() {
        return listalergias;
    }

    public void setListalergias(ArrayList<Alergia> listalergias) {
        this.listalergias = listalergias;
    }

    public ArrayList<Cirugia> getListacirugias() {
        return listacirugias;
    }

    public void setListacirugias(ArrayList<Cirugia> listacirugias) {
        this.listacirugias = listacirugias;
    }

    public ArrayList<Enfermedad> getListaenfermedades() {
        return listaenfermedades;
    }

    public void setListaenfermedades(ArrayList<Enfermedad> listaenfermedades) {
        this.listaenfermedades = listaenfermedades;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idPaciente);
        hash = 59 * hash + Objects.hashCode(this.infoContacto);
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.edad);
        hash = 59 * hash + Objects.hashCode(this.listalergias);
        hash = 59 * hash + Objects.hashCode(this.listacirugias);
        hash = 59 * hash + Objects.hashCode(this.listaenfermedades);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paciente other = (Paciente) obj;
        if (!Objects.equals(this.infoContacto, other.infoContacto)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.edad, other.edad)) {
            return false;
        }
        if (!Objects.equals(this.idPaciente, other.idPaciente)) {
            return false;
        }
        if (!Objects.equals(this.listalergias, other.listalergias)) {
            return false;
        }
        if (!Objects.equals(this.listacirugias, other.listacirugias)) {
            return false;
        }
        if (!Objects.equals(this.listaenfermedades, other.listaenfermedades)) {
            return false;
        }
        return true;
    }
    
}
