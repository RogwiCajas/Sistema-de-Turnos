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
    private static int id=1;//Solo para incrementar
    private int idP;

    public Paciente(String nombres, String apellidos, int edad, String genero,String sintoma) {
        super(nombres, apellidos, edad, genero);
        this.sintoma = sintoma;
        this.idP =id++;
    }
    public Paciente(String nombres, String apellidos, int edad, String genero,String sintoma, int idP) {
        super(nombres, apellidos, edad, genero);
        this.sintoma = sintoma;
        this.idP =idP;
        id++;
    }
    public Paciente(){
        super("-","-",0,"-");
        this.sintoma="-";
        
    }
    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    @Override
    public String toString() {
        if(this.getIdP()==0){
            return "Paciente no Asignado";
        }
        return "Paciente{" + "sintoma=" + sintoma + ", idP=" + idP + '}';
    }

    
    
    
    
    
}
