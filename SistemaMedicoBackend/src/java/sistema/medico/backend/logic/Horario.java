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
public class Horario {
    Integer medicoId;
    String dia;
    String apertura;
    String cierre;

    public Horario(Integer medicoId, String dia, String apertura, String cierre) {
        this.medicoId = medicoId;
        this.dia = dia;
        this.apertura = apertura;
        this.cierre = cierre;
    }
    public Horario() {
        this.dia = "";
        this.apertura ="";
        this.cierre = "";
        this.medicoId =0;
       
    }

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getApertura() {
        return apertura;
    }

    public void setApertura(String apertura) {
        this.apertura = apertura;
    }

    public String getCierre() {
        return cierre;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.medicoId);
        hash = 31 * hash + Objects.hashCode(this.dia);
        hash = 31 * hash + Objects.hashCode(this.apertura);
        hash = 31 * hash + Objects.hashCode(this.cierre);
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
        final Horario other = (Horario) obj;
        if (!Objects.equals(this.dia, other.dia)) {
            return false;
        }
        if (!Objects.equals(this.apertura, other.apertura)) {
            return false;
        }
        if (!Objects.equals(this.cierre, other.cierre)) {
            return false;
        }
        if (!Objects.equals(this.medicoId, other.medicoId)) {
            return false;
        }
        return true;
    }
     
}
