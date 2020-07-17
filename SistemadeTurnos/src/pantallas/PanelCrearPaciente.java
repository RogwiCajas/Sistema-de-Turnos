package pantallas;


import controlador.Controlador;
import static controlador.Controlador.getPacientes;
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
import modelo.Paciente;
import modelo.Puesto;


/**
 *
 * @author cajas
 */
public class PanelCrearPaciente extends Pane{
    //LinkedList<Medico> listamedicos;
    private Label label;
    private Button crearPaciente;
    private Button atras;
    //labels
    
    private Label lNombre;
    private Label lApellido;
    private Label lSintoma;
    private Label lEdad;
    private Label lGenero;
    private Label  lIdPaciente;

    //areas de texto
    private TextField nombretxt;
    private TextField apellidotxt;
    private TextField sintomatxt;
    private TextField edadtxt;
    private TextField generotxt;
     private TextField idPacientetxt;


     //atributo para mostrar tabla de puestos
    private HBox botones;
    private HBox crud;
    private VBox labels;
    private VBox ingresos;
    
    private VBox panel;
    private Pane root;
    
    public PanelCrearPaciente(Stage stage){
        iniciar( stage);
        
    }
    private void iniciar(Stage stage){
        //Iniciamos Root y panel principal
        
        this.panel=new VBox(5);
        this.root=new StackPane(panel);
        
        //nodos a usar
        this.label= new Label("Formulario de Nuevo Paciente ");
        this.crearPaciente= new Button("Crear Paciente");
         this.atras=new Button("Volver");
        
        this.lNombre=new Label("Nombre: ");
        this.lApellido=new Label("Apellidos: ");
        this.lSintoma=new Label("Sintoma: ");
        this.lEdad=new Label("Edad: ");
        this.lGenero=new Label("Genero: ");
        this.lIdPaciente=new Label("Cedula: ");
 
        
        this.nombretxt=new TextField();
        this.apellidotxt=new TextField();
        this.sintomatxt=new TextField();
        this.edadtxt=new TextField();
        this.generotxt=new TextField();
        this.idPacientetxt = new  TextField();
         
        //formatos de los nodos
        formato();
        //acciones de los nodos
        setearAcciones(stage);
        
        //Organizacion pantalla
        this.botones=new HBox(200);
        botones.getChildren().addAll(crearPaciente);
        this.crud=new HBox(20);
        this.labels=new VBox(15);
        this.ingresos=new VBox(5);
        labels.getChildren().addAll(lNombre,lApellido,lSintoma,lEdad,lGenero,lIdPaciente);
        ingresos.getChildren().addAll(nombretxt,apellidotxt,sintomatxt,edadtxt,generotxt,idPacientetxt);
        crud.getChildren().addAll(labels,ingresos);
        
        panel.getChildren().addAll(label,crud,botones,atras);//Vbox
        
        
    }
   
    private void agregarPaciente(){//Validar entradas segun tipo

        try {
            
            String nombre=this.nombretxt.getText();
            String apellidos=this.apellidotxt.getText();
            int edad=Integer.parseInt(this.edadtxt.getText());
            String genero=this.generotxt.getText();
            String sintoma=this.sintomatxt.getText();

            controlador.Controlador.agregarPaciente(nombre, apellidos, genero, edad, sintoma);
            Paciente p= getPacientes().peek();
            if(getPacientes().contains(p)){//para evitar repetir
                 Controlador.crearNotificacion("Confirmacion", "Paciente Creado Correctamente");
            } else{
                Controlador.crearAlerta("ERROR", "No se creo el paceinte");
            }
            
        } catch (NumberFormatException e) {
            Controlador.crearAlerta("EROR","Formato Incorrecto!");
        }
        
    }
    
        
    
    
    private void formato(){
        //formato y alineacion del panel principal
        panel.setPadding(new Insets(150,70,70,100));
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-background-color: Beige");
        
        //formato titulo
    
        label.setStyle(" -fx-text-fill: Black; -fx-font-size: 35; -fx-font-weight: BOLD; -fx-background-color: Cyan");
        label.setAlignment(Pos.CENTER);
        label.setLayoutX(400);
        label.setLayoutY(80);
        
        //Formato botones
        crearPaciente.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
        atras.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
              
        crearPaciente.setPrefSize(150, 40);
         atras.setPrefSize(150, 40);
        
        crearPaciente.setAlignment(Pos.CENTER);
         atras.setAlignment(Pos.CENTER);
        
    }
    
    private void vaciarText(){
        nombretxt.setText(null);
        apellidotxt.setText(null);
        edadtxt.setText(null);
        sintomatxt.setText(null);
        generotxt.setText(null);
    }
    
    private  void setearAcciones(Stage stage){
        crearPaciente.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            
                            agregarPaciente();
                            vaciarText();
			}
	});
       
        atras.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            
                            Scene escena= new Scene(new PanelPaciente(stage).getRoot(),700,700);
                            stage.setTitle("Menu Pacientes");
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

