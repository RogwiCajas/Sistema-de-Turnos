/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;
import controlador.Controlador;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Medico;
import modelo.Paciente;
import modelo.Puesto;

/**
 *
 * @author cajas
 */
public class PanelAdministrarPuestos extends Pane{
    private Label label;
    private Button crearPuesto;
    private Button eliminarPuesto;
    private Button atras;
    private Button asignar;
    //labels
    private Label lDisponible;
    //combobox de doctores
    private ComboBox<Medico> cb;
    
    //Tabla para mostrar Puestos
    private TableView<Puesto> tblpuestos;
    private TableColumn colIDM;
    private TableColumn colIDP;
    private TableColumn colEstado;
    
    private ObservableList<Puesto> puestos;
     //atributo para mostrar tabla de puestos
    private HBox botones;
    private HBox crud;
    private VBox labels;
    private VBox ingresos;
    
    private VBox panel;
    private Pane root;
    
    public PanelAdministrarPuestos(Stage stage){
        
        iniciar( stage);
    }
    private void iniciar(Stage stage){
        //Iniciamos Root y panel principal
        
        this.panel=new VBox(40);
        this.root=new StackPane(panel);
        
        //nodos a usar
        //nodos a usar
        this.label= new Label("ADMINISTRAR PUESTOS");
        this.crearPuesto= new Button("Crear Puesto");
        this.eliminarPuesto=new Button("Eliminar Puesto");
        this.atras=new Button("Volver");
        this.asignar=new Button("Cambiar Doctor");
        
        this.lDisponible=new Label("Escoga Doctor asignado: ");
        //cambiar por una lista global de Doctores desocupados
        this.cb=new ComboBox<>(FXCollections.observableList(Controlador.getDoctores()));
        //tabla
        cargarTabla();
        //formatos de los nodos
        formato();
        //acciones de los nodos
        setearAcciones(stage);
        //Llenamos el nodo root
        this.botones=new HBox(150);
        botones.getChildren().addAll(crearPuesto,asignar,eliminarPuesto);
        this.crud=new HBox(20);
        this.labels=new VBox(15);
        this.ingresos=new VBox(5);
        labels.getChildren().addAll(lDisponible);
        ingresos.getChildren().addAll(cb);
        crud.getChildren().addAll(labels,ingresos);
        
        panel.getChildren().addAll(label,tblpuestos,botones,crud,atras);//Vbox
        
    }
    private void agregarPuesto(){//Validar entradas segun tipo
        Puesto p;
        try {
            
            p=new Puesto(cb.getValue(),false,new Paciente());//sin asignar paciente
            
            if(!this.puestos.contains(p)){//para evitar repetir
                if(p.getDoctor()==null){
                    //p.setEstado(Boolean.FALSE);
                    p.setDoctor(new Medico());//ASIGNO UN MEDICO VACIO si no escogio ningun medico
                }
                if(p.getDoctor().getIdM()!=0 &&!Controlador.getPacientes().isEmpty()){
                    //desencolo un paciente si hay un doctor no nulo y si hay pacientes en cola
                    p.setPaciente(Controlador.getPacientes().poll());
                    p.setEstado(Boolean.TRUE);
                }
                this.puestos.add(p);
                this.tblpuestos.setItems(puestos);
                //Escribir puesto en puestos.txt
                Controlador.crearNotificacion("Confirmacion", "Puesto Creado Correctamente!");
                
            } else{
                Controlador.crearAlerta("ERROR", "El puesto ya existe!");
            }
            
        } catch (Exception e) {
            Controlador.crearAlerta("ERROR", "Error al agregar, consulte al desarrollador!");
        }
        //Medico m=new Medico(nombre, apellidos, edad, genero,especialidad);
        
    }
    private void asignarPuesto(Stage stage){
        try {
            //Obtengo el selected
            Puesto p=this.tblpuestos.getSelectionModel().getSelectedItem();
            //Hago una instancia distinta con los mismos datos
            Puesto pc=new Puesto();
            pc.setDoctor(p.getDoctor());
            pc.setEstado(p.getEstado());
            pc.setId(p.getId());
            pc.setPaciente(p.getPaciente());
            
            if(p.getEstado()==false && p.getDoctor().getIdM()==0){//si no hay un paciente asigando al puesto o no hay doctor
                // Obtengo el Medico del cb
                Medico m=this.cb.getValue();//no puede sr vacio
                if(m==null){
                    m=new Medico();// le mando un medico vacio para evitar NULLPOINTER
                }
                //cambio el doctor en la copia, para hacer el equals
                pc.setDoctor(m);
                
                if(puestos.contains(pc)){
                    System.out.println(p.toString());
                    Controlador.crearAlerta("ERROR", "Medico repetido!");
                    
                }else{
                    p.setDoctor(m);
                    System.out.println(p.toString());
                    //actualiza la vista
                    this.tblpuestos.setItems(puestos);
                    //agregar alerta exitosa
                    Controlador.crearNotificacion("Confirmacion", "Medico Asignado exitosamente!");
                    //Ahora que le setee un doctor compruebo que setee un doctor valido y le asigno paciente
                    if(p.getDoctor().getIdM()!=0 &&!Controlador.getPacientes().isEmpty()){
                    //desencolo un paciente si hay un doctor no nulo y si hay pacientes en cola
                            p.setPaciente(Controlador.getPacientes().poll());
                            p.setEstado(Boolean.TRUE);
                    }
                    
                    actualizarPantalla(stage);
                    
                }
                
                
                
            }else{//si no esta vacio, pide que porfavor elimines
                Controlador.crearAlerta("Uy kieto", "Puesto Ocupado: \nAntes de realizar cambios en el puesto el Paciente debe ser atendido!");
            }  
            
        } catch (Exception e) {
            Controlador.crearAlerta("ERROR", "Error inesperado!");
        }
        
    }
    private void actualizarPantalla(Stage stage){
        Scene  escena=new Scene(new PanelAdministrarPuestos(stage).getRoot(),700,700);
                            stage.setTitle("Administrar Puestos");
                            stage.setScene(escena);
                            stage.show();
        
    }
    private void eliminarPuesto(){
            try {
            //Obtengo el selected
            Puesto p=this.tblpuestos.getSelectionModel().getSelectedItem();
            //si tiene un Paciente no pude ser removido
            if(p.getEstado()==false){//si no hay un paciente asigando al puesto
                //elimina el puesto
                this.puestos.remove(p);
                //actualiza la vista
                this.tblpuestos.setItems(puestos);
                //agregar alerta exitosa
                //aqui deberia actualizar los puestos
                Controlador.crearNotificacion("Confirmacion", "Medico Eliminado Correctamente");
                
            }else{//si no esta vacio, pide que porfavor elimines
                Controlador.crearAlerta("Uy kieto", "Puesto Ocupado: \nAntes de eliminar el puesto el Paciente debe ser atendido!");
            }  
            
        } catch (Exception e) {
            Controlador.crearAlerta("ERROR", e.getMessage());
        }
        
    }
    private void cargarTabla(){
        
        this.puestos=FXCollections.observableList(Controlador.getPuestos());//setea medicos en la tabla
              
        this.tblpuestos=new TableView<>();
        this.colIDP=new TableColumn("ID Puesto");
        this.colIDM=new TableColumn("Medico");
        this.colEstado=new TableColumn("Ocupado");
               
        tblpuestos.getColumns().addAll(colIDP,colIDM,colEstado);
        tblpuestos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        this.colIDP.setCellValueFactory(new PropertyValueFactory("id"));
        this.colIDM.setCellValueFactory(new PropertyValueFactory("doctor"));
        this.colEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        
        //actualiza la tabla
        this.tblpuestos.setItems(puestos);
        
    }
    private void formato(){
        //formato y alineacion del panel principal
        panel.setPadding(new Insets(150,70,70,100));
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-background-color: Beige");
        
        //formato titulo
    
        label.setStyle("-fx-text-fill: Black; -fx-font-size: 35; -fx-font-weight: BOLD; -fx-background-color: Cyan");
        label.setAlignment(Pos.CENTER);
        label.setLayoutX(300);
        label.setLayoutY(80);
        
        //Formato botones
        crearPuesto.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
        eliminarPuesto.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
        atras.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
        asignar.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
              
        crearPuesto.setPrefSize(150, 40);
        eliminarPuesto.setPrefSize(150, 40);
        atras.setPrefSize(150, 40);
        asignar.setPrefSize(150, 40);
        
        crearPuesto.setAlignment(Pos.CENTER);
        eliminarPuesto.setAlignment(Pos.CENTER);
        atras.setAlignment(Pos.CENTER);
        asignar.setAlignment(Pos.CENTER);
        
    }
    private  void setearAcciones(Stage stage){
        crearPuesto.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            agregarPuesto();
                            
			}
	});
        eliminarPuesto.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            eliminarPuesto();
			}
	});
        asignar.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            asignarPuesto(stage);
                            
                            
			}
	});
        atras.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            //setea el root a la ventana siguiente
                            System.out.println("Presiono salir");
                            Scene escena= new Scene(new PanelAdministrar(stage).getRoot(),700,700);
                            stage.setScene(escena);
                            stage.show();
                            
                            
			}
	});
    }
    /*
    *Sirve para poder iniciar la pantalla donde sea que se la llame new Pantalla().getRoot()
    */
    public Pane getRoot(){
        return this.root;
    }
    public void setRoot(Pane nuevoRoot){
        this.root=nuevoRoot;
    }
}
