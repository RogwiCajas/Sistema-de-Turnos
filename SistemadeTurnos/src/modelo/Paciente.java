/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;/**
 *
 * @author cajas
 */
public class Paciente extends Persona{
    private String sintoma;
    private int prioritySintoma;
    private static int id=1;//Solo para incrementar
    private int idP;

    public Paciente(String nombres, String apellidos, int edad, String genero,String sintoma, int prioritySintoma) {
        super(nombres, apellidos, edad, genero);
        this.sintoma = sintoma;
        this.prioritySintoma = prioritySintoma;
        this.idP =id++;
    }
    public Paciente(String nombres, String apellidos, int edad, String genero,String sintoma, int prioritySintoma, int idP) {
        super(nombres, apellidos, edad, genero);
        this.sintoma = sintoma;
        this.prioritySintoma = prioritySintoma;
        this.idP =idP;
        id++;
    }
    public Paciente(){
        super("-","-",0,"-");
        this.sintoma="-";
        this.prioritySintoma=10;
        
    }
    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public int getPrioritySintoma() {
        return prioritySintoma;
    }

    public void setPrioritySintoma(int prioritySintoma) {
  
        
        this.prioritySintoma = prioritySintoma;
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
        return "Paciente{" +  "nombre="+this.getNombres()+" "+this.getApellidos()+  "sintoma=" + sintoma + ", Priodifad" + prioritySintoma + ", idP=" + idP + '}';
    }
    
    public String toString2() {
        if(this.getIdP()==0){
            return "Paciente no Asignado";
        }
        return "P-" + this.getNombres().substring(0, 1)+this.getApellidos().substring(0,1)+ sintoma.substring(0, 1) + idP;
    }
    
    
    
    
    
}
