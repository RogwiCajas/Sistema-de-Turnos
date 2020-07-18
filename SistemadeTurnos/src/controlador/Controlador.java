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
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import modelo.Medico;
import modelo.Paciente;
import modelo.Puesto;
import modelo.Sintoma;
import tda.CircularDoublyLinkedList;
import tda.Node;


/**
 *
 * @author cajas
 */
public class Controlador {
    
    private static LinkedList<Medico> doctores;
    private static LinkedList<Paciente> pacienteslista;
    private static LinkedList<Sintoma> sintomas;
    private static final PriorityQueue<Paciente> pacientes=new PriorityQueue<>((Paciente p1, Paciente p2)-> p1.getPrioritySintoma()- p2.getPrioritySintoma());
    private static LinkedList<Puesto> puestos;
    private static CircularDoublyLinkedList<String> videos;
    public static CircularDoublyLinkedList<String> getVideos() {
        return videos;
    }
    

    public static void iniciarSistema(){//Llama a todos los metodos de carga de archivos a sus respectivas listas
        cargarSintomas();
        cargarDoctores();
        cargarPacientesLista();
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
                          toBoolean(dato[2]),
                          pacientes.poll()); //con la cola de prioridad se asignaria cada paciente a los puestos
                          //buscarPacienteID(Integer.parseInt(dato[3])));          
                  puestos.add(p);
            }
            b.close();
            
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        }
        
    }
    /*
    Metodo para cargar los pacientes del archivo a una lista 
    */
    private static void cargarPacientesLista(){
        File file=new File("src/recursos/pacientes.txt");
        String linea;
        pacienteslista=new LinkedList<>();
        try {
            FileReader f =new FileReader(file);
            BufferedReader b=new BufferedReader(f);
            while((linea=b.readLine())!=null){
                String[] dato=linea.split(",");
                Paciente p=new Paciente(
                          dato[0], //nombre
                          dato[1], //apellido
                          Integer.parseInt(dato[2]), //edad 
                          dato[3], //genero
                          dato[4], //sintoma
                          asignaPrioridad(dato[4]), //prioidad del sintoma
                          Integer.parseInt(dato[5])//el id es incremental, no es necesario agregarlo
                  );
                  pacienteslista.add(p);
                  pacientes.add(p);
            }
            b.close();
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        }
     
        
    }
    
    //funcion para cargar el txt de sintomas 
    private static void cargarSintomas(){
        File file=new File("src/recursos/sintomas.txt");
        String linea;
        sintomas=new LinkedList<>();
        try {
            FileReader f =new FileReader(file);
            BufferedReader b=new BufferedReader(f);
            
            while((linea=b.readLine())!=null){
                  String[] dato=linea.split(",");
                  Sintoma s=new Sintoma(
                          dato[0],
                          Integer.parseInt(dato[1])
                  );
                  sintomas.add(s);
            }
            b.close();
            
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        } 
    }
    
    
    //funcion que agrega medicos al documento y a la lista
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
      
    //envia a escribir en el doct del historial  
    public static boolean agregarHistorial(int puesto, String medico, String paciente, String sintoma, String diagnostico, String receta){
        try {
            FileWriter fstream = new FileWriter("src/recursos/HistorialAtencion.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("\n Puesto de Atencion"+ puesto +", Medico; "+medico+", Paciente: "+paciente+", Sintoma: "+sintoma+", Diagnostico: "+diagnostico+", Receta: "+  receta);
            out.close();
            
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        return true;
    }
     
     //agreg los pacientes en el documento y en la cola y en la lista
    public static boolean agregarPaciente(String nombre, String apellido, String genero, int edad, String sintoma){
        try {
            //creamos primero el objeto para general la istnacia y el ID
            Paciente p;
            p=new Paciente(nombre, apellido, edad, genero,sintoma,asignaPrioridad(sintoma));
            pacientes.add(p);
            FileWriter fstream = new FileWriter("src/recursos/pacientes.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("\n"+nombre+","+apellido+","+genero+","+edad+","+sintoma+","+  p.getIdP());
            out.close();
            
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        return true;
    }
    
    //busca puestos en el la lista
    private static Puesto buscarPuesto(int id){
        Iterator<Puesto> it=puestos.iterator();   
        while(it.hasNext()){
            Puesto p=it.next();
            if(p.getId()==id){//si es el id que buscamos
                return p;
            }            
        }
        return null;     
    }
    
    //busca medicos en la lista de dcotores
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
    
    //busca pacientes en la lista de pacientes
    private static Paciente buscarPacienteID(int id){
        if (id==0){//no tiene paciente asignado
            return new Paciente();
        }
        Iterator<Paciente> it=pacienteslista.iterator();
        
        while(it.hasNext()){
            Paciente p=it.next();
            if(p.getIdP()==id){//si es el id que buscamos
                return p;
            }            
        }
        return null;     
    }
    
    //obitene el nombre del doctor, y el paciente
    public static String obtenerNombre(int id, int tipo){
        //el tipo decide donde voy a buscar
        Puesto p = buscarPuesto(id);
        String nombreCompleto;
        switch (tipo) {
            case 1:
                Medico m= p.getDoctor();
                nombreCompleto = m.getNombres() + " " + m.getApellidos();
                break;
            case 2:
                {
                    Paciente pa= p.getPaciente();
                    nombreCompleto = pa.getNombres() + " " + pa.getApellidos();
                    break;
                }
            default:
                {
                    Paciente pa= p.getPaciente();
                    nombreCompleto = pa.getSintoma();
                    break;
                }
        };
        return nombreCompleto;
    }
    
     
    private static Boolean toBoolean(String i){
        if(i.equals("true")){
            return true;
        }else{
            return false;
        }
    }
    
    //wlimina la asignacion del doctor
    public static void eliminarAsignacionPuestoMedico(Medico m){
        Iterator<Puesto> it=puestos.iterator();
        //creo que primero debriamos prenguar tambien si el el puesto no tiene un paciente
        while(it.hasNext()){
            Puesto p=it.next();
            if(p.getDoctor().equals(m)){//Cuando encuentre al puesto con el medico
                p.setDoctor(new Medico());//agrego un objeto medico vacio
                p.setEstado(Boolean.FALSE);
                
            }
        }
    }
    public static void eliminarAsignacionPuestoPaciente(int idPuesto){
        Iterator<Puesto> it=puestos.iterator();
        while(it.hasNext()){
            Puesto p=it.next();
            if(p.getId()==idPuesto){//Cuando encuentre al puesto con el paciete
                pacienteslista.remove(p.getDoctor());
                p.setPaciente(new Paciente());//agrego un objeto paciente vacio                
            }
            
        }
    }
    
    //asigna nuevo paciente cuando se termina con la atencion
    public static void asignarNuevoPaciente(int idPuesto){
        Iterator<Puesto> it=puestos.iterator();
        while(it.hasNext()){
            Puesto p=it.next();
            if(p.getId()==idPuesto){//Cuando encuentre al puesto con el paciete
                p.setPaciente(pacientes.poll());//agrego un objeto paciente vacio                
            }
        }
    }
    
   
    
    public static void crearAlerta(String titulo,String mensaje){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    public static void crearNotificacion(String titulo,String mensaje){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
   public static int asignaPrioridad(String sintoma){
       int prioridad=10;
       for(int i=0; i<sintomas.size();i++){
          if(sintomas.get(i).getNombreSintoma().equals(sintoma)){
              prioridad=sintomas.get(i).getPrioridad();
          }
       }
       return prioridad;
   }
   
    
    /**
     * Funcion que se encarga de llenar la lista circula doble 
     * @param rutaProyecto 
     */
    public static void llenarListaCircular(String rutaProyecto) {
        File file=new File("src/recursos/videos.txt");
        String linea;
        videos=new CircularDoublyLinkedList<>();
        
        try {
            FileReader f =new FileReader(file);
            BufferedReader b=new BufferedReader(f);
            
            while((linea=b.readLine())!=null){
                  
                  videos.addLast(rutaProyecto + "\\videos\\" +linea);
            }
            b.close();
            
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        } 
    }
    /*public static void llenarListaCircular(String rutaProyecto) {
        videos = new CircularDoublyLinkedList<>();
        String[] nombreVideos = {"zoom_1", "zoom_2", "zoom_3"};
        for (String nombreVideo : nombreVideos) {
            videos.addLast(rutaProyecto + "\\videos\\" + nombreVideo + ".mp4");
            
        }
       
       
        }*/

   
    
        
        
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

    public static LinkedList<Puesto> getPuestos() {
        return puestos;
    }

    public static void setPuestos(LinkedList<Puesto> puestos) {
        Controlador.puestos = puestos;
    }
    
    
}
