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
public class Medico extends Persona{
    private String especialidad;
    private int id;

    public Medico(String nombres, String apellidos, int edad, String genero,String especialidad,int id) {
        super(nombres, apellidos, edad, genero);
        this.especialidad = especialidad;
        this.id=id;
    }
    public Medico(int id){
        super(null, null, 0, null);
        this.especialidad = null;
        this.id=id;
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
