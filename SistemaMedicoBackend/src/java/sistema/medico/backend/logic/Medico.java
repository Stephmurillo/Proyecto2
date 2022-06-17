package sistema.medico.backend.logic;

import java.util.ArrayList;
import java.util.Objects;

public class Medico extends User{
    private String costo;
    private String nombre;
    private int estado;
    private String descripcion;
    private String direccion;
    private String especialidad;
    private String localidad;
    Integer tiempoCitas;
    ArrayList<Horario> listahorarios;

    public Medico(String costo, String nombre, int estado, String descripcion, String direccion, String especialidad, String localidad, Integer tiempoCitas, ArrayList<Horario> listahorarios, Integer idUser, String password, int roll) {
        super(idUser, password, roll);
        this.costo = costo;
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.especialidad = especialidad;
        this.localidad = localidad;
        this.tiempoCitas = tiempoCitas;
        this.listahorarios = listahorarios;
    }
     public Medico(String costo, String nombre, int estado, String descripcion, String direccion, String especialidad, String localidad, Integer tiempoCitas, ArrayList<Horario> listahorarios) {
     
        this.costo = costo;
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.especialidad = especialidad;
        this.localidad = localidad;
        this.tiempoCitas = tiempoCitas;
        this.listahorarios = listahorarios;
    }
    public Medico() {
        this.nombre ="" ;
        this.costo = "";
        this.descripcion = "";
        this.direccion = "";
        this.localidad = "";
        this.especialidad = "";
        this.listahorarios =  new ArrayList<>();
        this.estado = 0;
        this.tiempoCitas = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public ArrayList<Horario> getListahorarios() {
        return listahorarios;
    }

    public void setListahorarios(ArrayList<Horario> listahorarios) {
        this.listahorarios = listahorarios;
    }

    public Integer getTiempoCitas() {
        return tiempoCitas;
    }

    public void setTiempoCitas(Integer tiempoCitas) {
        this.tiempoCitas = tiempoCitas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.costo);
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + this.estado;
        hash = 29 * hash + Objects.hashCode(this.descripcion);
        hash = 29 * hash + Objects.hashCode(this.direccion);
        hash = 29 * hash + Objects.hashCode(this.especialidad);
        hash = 29 * hash + Objects.hashCode(this.localidad);
        hash = 29 * hash + Objects.hashCode(this.tiempoCitas);
        hash = 29 * hash + Objects.hashCode(this.listahorarios);
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
        final Medico other = (Medico) obj;
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.costo, other.costo)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.especialidad, other.especialidad)) {
            return false;
        }
        if (!Objects.equals(this.localidad, other.localidad)) {
            return false;
        }
        if (!Objects.equals(this.tiempoCitas, other.tiempoCitas)) {
            return false;
        }
        if (!Objects.equals(this.listahorarios, other.listahorarios)) {
            return false;
        }
        return true;
    }
     
}