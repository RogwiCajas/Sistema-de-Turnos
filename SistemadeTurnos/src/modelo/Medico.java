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
    private static int id=1;//Solo para incrementar
    private int idM;

    public Medico(String nombres, String apellidos, int edad, String genero,String especialidad) {
        super(nombres, apellidos, edad, genero);
        this.especialidad = especialidad;
        this.idM=id++;
    }
    public Medico(String nombres, String apellidos, int edad, String genero,String especialidad,int idM) {
        super(nombres, apellidos, edad, genero);
        this.especialidad = especialidad;
        this.idM=idM;
        id++;
    }
    public Medico(){
        super("-","-",0,"-");
        this.especialidad="-";
        
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

    public int getIdM() {
        return idM;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    @Override
    public String toString() {
        if(this.idM==0){
            return "Medico no Asignado";
        }
        return "Medico{" +"nombre="+this.getNombres()+" "+this.getApellidos()+ ", especialidad=" + especialidad + ", ID Medico=" + idM + '}';
    }
    
    
    
    
    
    
}
