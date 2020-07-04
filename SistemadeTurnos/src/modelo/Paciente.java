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
public class Paciente extends Persona{
    private String sintoma;

    public Paciente(String nombres, String apellidos, int edad, String genero,String sintoma) {
        super(nombres, apellidos, edad, genero);
        this.sintoma = sintoma;
                
    }

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }
    
    
    
}
