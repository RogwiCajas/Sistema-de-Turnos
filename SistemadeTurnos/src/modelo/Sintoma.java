/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author IsaacSolis
 */
public class Sintoma {
    String NombreSintoma;
    int prioridad;

    @Override
    public String toString() {
        return "Sintoma{" + "NombreSintoma=" + NombreSintoma + ", prioridad=" + prioridad + '}';
    }
    public String toString2() {
        return NombreSintoma ;
    }

    public Sintoma(String NombreSintoma, int prioridad) {
        this.NombreSintoma = NombreSintoma;
        this.prioridad = prioridad;
    }

    public String getNombreSintoma() {
        return NombreSintoma;
    }

    public void setNombreSintoma(String NombreSintoma) {
        this.NombreSintoma = NombreSintoma;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    
    
            
    
}
