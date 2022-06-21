/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.medico.backend.logic;

import java.util.Objects;

/**
 *
 * @author PC
 */
public class Enfermedad {
    String idPaciente;
    String nombre;

    public Enfermedad(String idPaciente, String nombre) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
    }

    public Enfermedad() {
        this.idPaciente = "";
        this.nombre = "";
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.idPaciente);
        hash = 53 * hash + Objects.hashCode(this.nombre);
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
        final Enfermedad other = (Enfermedad) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.idPaciente, other.idPaciente)) {
            return false;
        }
        return true;
    }
    
}
