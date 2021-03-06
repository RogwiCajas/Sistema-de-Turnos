/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Objects;

/**
 *
 * @author cajas
 */
public class Puesto {
    private static int idI=1;
    private int id;
    private Medico doctor;
    private Boolean estado;
    private Paciente paciente;

    public Puesto( Medico doctor, Boolean estado, Paciente p) {
        this.id = Puesto.idI++;
        this.doctor = doctor;
        this.estado = estado;
        this.paciente=p;
    }
    public Puesto( int id,Medico doctor, Boolean estado, Paciente p) {
        idI++;
        this.id = id;
        this.doctor = doctor;
        this.estado = estado;
        this.paciente=p;
    }
    public Puesto(){//usar para crear instancias vacias
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medico getDoctor() {
        return doctor;
    }

    public void setDoctor(Medico doctor) {
        this.doctor = doctor;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public static int getIdI() {
        return idI;
    }

    public static void setIdI(int idI) {
        Puesto.idI = idI;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Puesto other = (Puesto) obj;
        //if (this.id != other.id) {
        //    return false;
        //}
        if (!Objects.equals(this.doctor, other.doctor)) {
            return false;
        }
        /*if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }*/
        return true;
    }

    @Override
    public String toString() {
        return "Puesto{" + "id=" + id + ", doctor=" + doctor + ", estado=" + estado + "paciente:" + paciente + '}';
    }
    
    
    
}
