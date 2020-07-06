/*
 * Clase destinada al manejo de Datos del sistema, esto es lectura, carga y escritura de
 * archivos txt en las didstintas estructuras de datos que manejan la informacion del sistema
 * 
 */
package controlador;

import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import modelo.Medico;
import modelo.Paciente;
import modelo.Puesto;
import tda.CircularSimplyLinkedList;

/**
 *
 * @author cajas
 */
public class Controlador {
    
    private static LinkedList<Medico> doctores;
    private static PriorityQueue <Paciente> pacientes;
    private static LinkedList<Puesto> puestos;
    private static CircularSimplyLinkedList<String> videos;
    
    //metodos para cargar los arreglos
    public static LinkedList<Medico> cargarMedicos(){
        File file=new File("src/recursos/medicos.txt");
        String linea;
        LinkedList<Medico> procesos=new LinkedList<>();
        try {
            FileReader f =new FileReader(file);
            BufferedReader b=new BufferedReader(f);
            while((linea=b.readLine())!=null){
                  String[] dato=linea.split(",");
                  Medico m=new Medico(
                          dato[0],
                          dato[1],
                          Integer.parseInt(dato[2]),
                          dato[3],
                          dato[4],
                          Integer.parseInt(dato[5])
                  );
                  
                  procesos.add(m);
            }
            b.close();
            
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        }
        
        return procesos;
    }
    
   public static boolean agregarMedicos(String nombre, String apellido, String genero, int edad, String especialidad){
        try {
            FileWriter fstream = new FileWriter("src/recursos/medicos.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("\n"+nombre+","+apellido+","+genero+","+edad+","+especialidad);
            out.close();
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        return true;
 }
   
    public static PriorityQueue<Paciente> cargarPacientes(){//Cargar una lista de pacientes y meterlos en una cola
        File file=new File("src/recursos/pacientes.txt");
        String linea;
        LinkedList<Paciente> pacientes=new LinkedList<>();
        try {
            FileReader f =new FileReader(file);
            BufferedReader b=new BufferedReader(f);
            while((linea=b.readLine())!=null){
                  String[] dato=linea.split(",");
                  Paciente m=new Paciente(
                          dato[0],
                          dato[1],
                          Integer.parseInt(dato[2]),
                          dato[3],
                          dato[4]                            
                  );
                  
                  pacientes.add(m);
            }
            b.close();
            
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        }
        //Cargo la cola
        PriorityQueue<Paciente> colaPacientes=new PriorityQueue<>();
        colaPacientes.addAll(pacientes);
        return colaPacientes;
    }
    
    public static boolean agregarPaciente(String nombre, String apellido, String genero, int edad, String sintoma){
        try {
            FileWriter fstream = new FileWriter("src/recursos/pacientes.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("\n"+nombre+","+apellido+","+genero+","+edad+","+sintoma);
            out.close();
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        return true;
 }
    /*
    public static LinkedList<Puesto> cargarPuestos(){
        File file=new File("puestos.txt");
        String linea;
        LinkedList<Puesto> puestos=new LinkedList<>();
        try {
            FileReader f =new FileReader(file);
            BufferedReader b=new BufferedReader(f);
            while((linea=b.readLine())!=null){
                  String[] dato=linea.split(",");
                  Puesto p=new Puesto(
                          Integer.parseInt(dato[0]),
                          new Medico(Integer.parseInt(dato[1])), //crea un medico null
                          Boolean.getBoolean(dato[2])
                                       
                  );
                  
                  puestos.add(p);
            }
            b.close();
            
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        }
        
        return puestos;
    }
    
    */
}
