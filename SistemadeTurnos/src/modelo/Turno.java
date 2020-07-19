/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Alejandra Quimi
 */
public class Turno {
    private static int aumentador=1;
    private int id;
    private Puesto puesto;
    public Turno(Puesto puesto) {
        this.id = Turno.aumentador++;
        this.puesto=puesto;
        
    }

    public static int getAumentador() {
        return aumentador;
    }

    public static void setAumentador(int aumentador) {
        Turno.aumentador = aumentador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
    
    
    
    
    
    
}
