/*
 * Clase destinada al manejo de Datos del sistema, esto es lectura, carga y escritura de
 * archivos txt en las didstintas estructuras de datos que manejan la informacion del sistema
 * 
 */
package controlador;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import modelo.Medico;
import modelo.Paciente;
import modelo.Puesto;
import tda.CircularDoublyLinkedList;
import tda.Node;


/**
 *
 * @author cajas
 */
public class Controlador {
    
    private static LinkedList<Medico> doctores;
    private static PriorityQueue <Paciente> pacientes;
    private static LinkedList<Puesto> puestos;
    private static CircularDoublyLinkedList<String> videos;

    public static CircularDoublyLinkedList<String> getVideos() {
        return videos;
    }
    

    
    
    
    public static void iniciarSistema(){//Llama a todos los metodos de carga de archivos a sus respectivas listas
        cargarDoctores();
        cargarPuestos();
    }
   
    
    
    //metodos para cargar los arreglos
    private static void cargarDoctores(){
        File file=new File("src\\recursos\\medicos.txt");
        String linea;
        doctores=new LinkedList<>();
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
                          Integer.parseInt(dato[5])//el id es incremental, no es necesario agregarlo
                  );
                  
                  doctores.add(m);
            }
            b.close();
            
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        }
        
        
    }
    /*
    * Funcion que carga los Puestos del archivo plano a la Lista global
    */
    private static void cargarPuestos(){
        File file=new File("src\\recursos\\puestos.txt");
        String linea;
        puestos=new LinkedList<>();
        try {
            FileReader f =new FileReader(file);
            BufferedReader b=new BufferedReader(f);
            while((linea=b.readLine())!=null){
                  String[] dato=linea.split(",");
                  Puesto p=new Puesto(
                          Integer.parseInt(dato[0]),
                          buscarMedicoID(Integer.parseInt(dato[1])), //busca el id y guarda el medico
                          toBoolean(dato[2])
                                       
                  );
                  
                  puestos.add(p);
            }
            b.close();
            
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        }
        
    }
    private static Boolean toBoolean(String i){
        if(i.equals("true")){
            return true;
        }else{
            return false;
        }
    }
    private static Medico buscarMedicoID(int id){
        Iterator<Medico> it=doctores.iterator();
        
        while(it.hasNext()){
            Medico m=it.next();
            if(m.getIdM()==id){//si es el id que buscamos
                return m;
            }
            
        }
        return null;
        
        
    }
    
    
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
                          dato[4]//el id es incremental, no es necesario agregarlo
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
    
    
    /**
     * Funcion que se encarga de llenar la lista circula doble 
     * @param rutaProyecto 
     */
    
    public static void llenarListaCircular(String rutaProyecto) {
        videos = new CircularDoublyLinkedList<>();
        String[] nombreVideos = {"zoom_1", "zoom_2", "zoom_3"};
        for (String nombreVideo : nombreVideos) {
            videos.addLast(rutaProyecto + "\\videos\\" + nombreVideo + ".mp4");
            
        }
       
       
        }

   
    
        
        
    //GETTER Y SETTER

    public static LinkedList<Medico> getDoctores() {
        return doctores;
    }

    public static void setDoctores(LinkedList<Medico> doctores) {
        Controlador.doctores = doctores;
    }

    public static PriorityQueue<Paciente> getPacientes() {
        return pacientes;
    }

    public static void setPacientes(PriorityQueue<Paciente> pacientes) {
        Controlador.pacientes = pacientes;
    }

    public static LinkedList<Puesto> getPuestos() {
        return puestos;
    }

    public static void setPuestos(LinkedList<Puesto> puestos) {
        Controlador.puestos = puestos;
    }
    
    
}
