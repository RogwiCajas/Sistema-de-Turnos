/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;


import controlador.Controlador;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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


/**
 *
 * @author cajas
 */
public class PanelAdministrarDoctores extends Pane{
    //LinkedList<Medico> listamedicos;
    private Label label;
    private Button crearDoctor;
    private Button eliminarDoctor;
    private Button atras;
    //labels
    private Label lNombre;
    private Label lapellido;
    private Label lespecialidad;
    private Label ledad;
    private Label lgenero;
    //areas de texto
    private TextField nombretxt;
    private TextField apellidotxt;
    private TextField especialidadtxt;
    private TextField edadtxt;
    private TextField generotxt;
    //Tabla para mostrar Doctores
    private TableView<Medico> tbldoctores;
    private TableColumn colNombre;
    private TableColumn colApellidos;
    private TableColumn colEspecialidad;
    private TableColumn colEdad;
    private TableColumn colGenero;
    private TableColumn colId;
    private ObservableList<Medico> doctores;
     //atributo para mostrar tabla de puestos
    private HBox botones;
    private HBox crud;
    private VBox labels;
    private VBox ingresos;
    
    private VBox panel;
    private Pane root;
    
    public PanelAdministrarDoctores(Stage stage){
        iniciar( stage);
        
    }
    private void iniciar(Stage stage){
        //Iniciamos Root y panel principal
        
        this.panel=new VBox(5);
        this.root=new StackPane(panel);
        
        //nodos a usar
        this.label= new Label("ADMINISTRAR DOCTORES");
        this.crearDoctor= new Button("Crear Doctor");
        this.eliminarDoctor=new Button("Eliminar Doctor");
        this.atras=new Button("Volver");
        
        this.lNombre=new Label("Nombre: ");
        this.lapellido=new Label("Apellidos: ");
        this.lespecialidad=new Label("Espcialidad: ");
        this.ledad=new Label("Edad: ");
        this.lgenero=new Label("Genero: ");
        
        this.nombretxt=new TextField();
        this.apellidotxt=new TextField();
        this.especialidadtxt=new TextField();
        this.edadtxt=new TextField();
        this.generotxt=new TextField();
        //Cargar tabla y sus columnas
        cargarTabla();
        
        //formatos de los nodos
        formato();
        //acciones de los nodos
        setearAcciones(stage);
        
        //Organizacion pantalla
        this.botones=new HBox(200);
        botones.getChildren().addAll(crearDoctor,eliminarDoctor);
        this.crud=new HBox(20);
        this.labels=new VBox(15);
        this.ingresos=new VBox(5);
        labels.getChildren().addAll(lNombre,lapellido,lespecialidad,ledad,lgenero);
        ingresos.getChildren().addAll(nombretxt,apellidotxt,especialidadtxt,edadtxt,generotxt);
        crud.getChildren().addAll(labels,ingresos);
        
        panel.getChildren().addAll(label,tbldoctores,botones,crud,atras);//Vbox
        
        
    }
    private void cargarTabla(){
        
        this.doctores=FXCollections.observableList(Controlador.getDoctores());//setea medicos en la tabla
              
        this.tbldoctores=new TableView<>();
        this.colNombre=new TableColumn("Nombre");
        this.colApellidos=new TableColumn("Apellidos");
        this.colEdad=new TableColumn("Edad");
        this.colGenero=new TableColumn("Genero");
        this.colId=new TableColumn("Id");
        this.colEspecialidad=new TableColumn("Especialidad");
        
        tbldoctores.getColumns().addAll(colNombre,colApellidos,colEdad,colGenero,colId,colEspecialidad);
        tbldoctores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombres"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory("edad"));
        this.colGenero.setCellValueFactory(new PropertyValueFactory("genero"));
        this.colEspecialidad.setCellValueFactory(new PropertyValueFactory("especialidad"));
        this.colId.setCellValueFactory(new PropertyValueFactory("idM"));
        //actualiza la tabla
        this.tbldoctores.setItems(doctores);
        
    }
    private void agregarMedico(){//Validar entradas segun tipo

        try {
            
            String nombre=this.nombretxt.getText();
            String apellidos=this.apellidotxt.getText();
            int edad=Integer.parseInt(this.edadtxt.getText());
            String genero=this.generotxt.getText();
            String especialidad=this.especialidadtxt.getText();
            
            Medico m;
            m=new Medico(nombre, apellidos, edad, genero,especialidad);
            
            if(!this.doctores.contains(m)){//para evitar repetir
                this.doctores.add(m);
                this.tbldoctores.setItems(doctores);
                Controlador.crearNotificacion("Confirmacion", "Medico Creado Correctamente");
                //rolador.crearNotificacion("Confirmacion", "Medico Creado Correctamente");agregar alerta exitosa
            } else{
                Controlador.crearAlerta("ERROR", "El medico ya existe!");
            }
            
        } catch (NumberFormatException e) {
            Controlador.crearAlerta("EROR","Formato Incorrecto!");
        }
        
    }
    private void eliminarMedico(){
        try {
            //Obtengo el selected
            Medico m=this.tbldoctores.getSelectionModel().getSelectedItem();
            //Buscaa y elimina asignaciones solo si el puesto no este ocupaado.
            if(Controlador.eliminarAsignacionPuestoMedico(m)==true){
                //elimina el doctor de la base
                this.doctores.remove(m);
                //actualiza la vista
                this.tbldoctores.setItems(doctores);
                //System.out.println(Controlador.getDoctores());
                //agregar alerta exitosa
                Controlador.crearNotificacion("Confirmacion", "Medico Eliminado Correctamente");
            }else{
                Controlador.crearAlerta("Cuidado", "Este medico se encuentra asignado a un puesto con paciente, Primero debe acabar de atender al paciente!");
            }
            
            
        } catch (Exception e) {
            Controlador.crearAlerta("ERROR", e.getMessage());
            
        }
        
        
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
        crearDoctor.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
        eliminarDoctor.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
        atras.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
              
        crearDoctor.setPrefSize(150, 40);
        eliminarDoctor.setPrefSize(150, 40);
        atras.setPrefSize(150, 40);
        
        crearDoctor.setAlignment(Pos.CENTER);
        eliminarDoctor.setAlignment(Pos.CENTER);
        atras.setAlignment(Pos.CENTER);
        
    }
    private  void setearAcciones(Stage stage){
        crearDoctor.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            
                            agregarMedico();
			}
	});
        eliminarDoctor.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            
                            eliminarMedico();
			}
	});
        atras.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            
                            Scene escena= new Scene(new PanelAdministrar(stage).getRoot(),700,700);
                            stage.setTitle("Menu Administrar");
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

