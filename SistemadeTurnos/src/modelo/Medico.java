/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author cajas
 */
public class Medico extends Persona {
    private String especialidad;
    private static int id=0;

    public Medico(String nombres, String apellidos, int edad, String genero,String especialidad,int id) {
        super(nombres, apellidos, edad, genero);
        this.especialidad = especialidad;
        incremnentarId();
    }
    public Medico(){
        super(null, null, 0, null);
        this.especialidad = null;
        incremnentarId();
    }
    private static int incremnentarId(){
        return id++;
    }
    public static int getId(){
        return id;
    }

    public Medico(String nombres, String apellidos, int edad, String genero) {
        super(nombres, apellidos, edad, genero);
    }
    
    

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
    
    
    
    
}
