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
public class Cita {
    String codigo;
    String estado;
    String paciente;
    Integer medico;
    String fecha;
    String motivo;
    String signos;
    String diagnostico;
    String prescMedicas;

    public Cita(String codigo, String estado, String paciente, Integer medico, String fecha, String motivo, String signos, String diagnostico, String prescMedicas) {
        this.codigo = codigo;
        this.estado = estado;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.motivo = motivo;
        this.signos = signos;
        this.diagnostico = diagnostico;
        this.prescMedicas = prescMedicas;
    }

    public Cita() {
        this.codigo = "";
        this.diagnostico ="";
        this.estado = "";
        this.fecha = "";
        this.medico = 0;
        this.motivo ="";
        this.paciente = "";
        this.prescMedicas ="";
        this.signos ="";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public Integer getMedico() {
        return medico;
    }

    public void setMedico(Integer medico) {
        this.medico = medico;
    }

   

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getSignos() {
        return signos;
    }

    public void setSignos(String signos) {
        this.signos = signos;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPrescMedicas() {
        return prescMedicas;
    }

    public void setPrescMedicas(String prescMedicas) {
        this.prescMedicas = prescMedicas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.codigo);
        hash = 67 * hash + Objects.hashCode(this.estado);
        hash = 67 * hash + Objects.hashCode(this.paciente);
        hash = 67 * hash + Objects.hashCode(this.medico);
        hash = 67 * hash + Objects.hashCode(this.fecha);
        hash = 67 * hash + Objects.hashCode(this.motivo);
        hash = 67 * hash + Objects.hashCode(this.signos);
        hash = 67 * hash + Objects.hashCode(this.diagnostico);
        hash = 67 * hash + Objects.hashCode(this.prescMedicas);
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
        final Cita other = (Cita) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.motivo, other.motivo)) {
            return false;
        }
        if (!Objects.equals(this.signos, other.signos)) {
            return false;
        }
        if (!Objects.equals(this.diagnostico, other.diagnostico)) {
            return false;
        }
        if (!Objects.equals(this.prescMedicas, other.prescMedicas)) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        if (!Objects.equals(this.medico, other.medico)) {
            return false;
        }
        return true;
    }
    
}