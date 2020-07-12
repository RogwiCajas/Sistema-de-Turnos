/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;


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
    LinkedList<Medico> listamedicos;
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
        
        //this.doctores=FXCollections.observableArrayList();
        this.listamedicos=new LinkedList<>();
        listamedicos.add(new Medico("isaac","solis",28,"M","Medicina general"));
        this.doctores=FXCollections.observableList(listamedicos);//setea medicos en la tabla
               
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
        this.colId.setCellValueFactory(new PropertyValueFactory("id"));
        //formatos de los nodos
        formato();
        //acciones de los nodos
        setearAcciones(stage);
        //Organizacion pantalla
        this.botones=new HBox(200);
        botones.getChildren().addAll(crearDoctor,eliminarDoctor);
        this.crud=new HBox(20);
        this.labels=new VBox(5);
        this.ingresos=new VBox(5);
        labels.getChildren().addAll(lNombre,lapellido,lespecialidad,ledad,lgenero);
        ingresos.getChildren().addAll(nombretxt,apellidotxt,especialidadtxt,edadtxt,generotxt);
        crud.getChildren().addAll(labels,ingresos);
        
        panel.getChildren().addAll(label,tbldoctores,botones,crud,atras);//Vbox
        
        
    }
    private void agregarMedico(){
        String nombre=this.nombretxt.getText();
        String apellidos=this.apellidotxt.getText();
        int edad=Integer.parseInt(this.edadtxt.getText());
        String genero=this.generotxt.getText();
        String especialidad=this.especialidadtxt.getText();
        //el ide se autogenera;
        Medico m;
        try {
            m=new Medico(nombre, apellidos, edad, genero,especialidad);
            if(!this.doctores.contains(m)){//para evitar repetir
                this.doctores.add(m);
                this.tbldoctores.setItems(doctores);
            } else{
                System.err.println("Usuario Repetido");
            }
            
        } catch (Exception e) {
            System.err.println("Complete todos los campos");
        }
        //Medico m=new Medico(nombre, apellidos, edad, genero,especialidad);
        
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
                            //setea el root a la ventana siguiente
                            System.out.println("Guarda doctor");
                            agregarMedico();//cambiar par que guarde en la tabla y en la lista global
			}
	});
        eliminarDoctor.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            //setea el root a la ventana siguiente
                            System.out.println("elimina doctor");
			}
	});
        atras.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            //setea el root a la ventana siguiente
                            System.out.println("Vulve al menu admin");
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

