package pantallas;


import controlador.Controlador;
import static controlador.Controlador.obtenerNombre;
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
import static pantallas.PanelAtenderPaciente.puestoEscogido;


/**
 *
 * @author cajas
 */
public class PanelAtencion extends Pane{
    //LinkedList<Medico> listamedicos;
    private Label label;
    private Button atenderPaciente;
    private Button atras;
    //labels
    
    private Label lPuesto;
    private Label lPuestoResult;
    private Label lDoctor;
    private Label lDoctorResult;
    private Label lPaciente;
    private Label lPacienteResult; //seria el nombre y el apellido
    private Label lSintoma;
    private Label lSintomaResult;
    private Label lDiagostico;
    private Label lRecete;

    //areas de texto
    private TextField diagnositotxt;
    private TextField recetatxt;
   

     //atributo para mostrar tabla de puestos
    private HBox botones;
    private HBox crud;
    private VBox labels;
    private VBox ingresos;
    
    private VBox panel;
    private Pane root;

    
//LinkedList<Puesto> Puestos = controlador.Controlador.getPuestos();
    
    
    
    public PanelAtencion(Stage stage){
        iniciar(stage);
        
    }
    private void iniciar(Stage stage){
        //Iniciamos Root y panel principal
        
        this.panel=new VBox(5);
        this.root=new StackPane(panel);
        
        //nodos a usar
        this.label= new Label("Atender al Paciente ");
        this.atenderPaciente= new Button("Finalizar Atencion");
        this.atras=new Button("Volver");
        
        this.lPuesto=new Label("No de Puesto: ");
        this.lDoctor=new Label("Doctor Encargado: ");
        this.lPaciente=new Label("Nombre del Paciete: ");
        this.lSintoma=new Label("Sintoma: ");
        this.lDiagostico=new Label("Escribir Diagnostico: ");
        this.lRecete=new Label("Escribir Receta: ");
 
        this.lPuestoResult=new Label(String.valueOf(puestoEscogido));
        this.lDoctorResult=new Label(obtenerNombre(puestoEscogido,1)); //con 1 se obtiene doctor
        this.lPacienteResult=new Label(obtenerNombre(puestoEscogido,2)); // con 2 se obtiene paciente
        this.lSintomaResult=new Label(obtenerNombre(puestoEscogido, 3)); //con 3 se obtiene sintoma
        this.diagnositotxt=new TextField();
        this.recetatxt=new TextField();
     
        //formatos de los nodos
        formato();
        //acciones de los nodos
        setearAcciones(stage);
        
        //Organizacion pantalla
        this.botones=new HBox(200);
        botones.getChildren().addAll(atenderPaciente);
        this.crud=new HBox(20);
        this.labels=new VBox(15);
        this.ingresos=new VBox(5);
        labels.getChildren().addAll(lPuesto,lDoctor,lPaciente,lSintoma,lDiagostico,lRecete);
        ingresos.getChildren().addAll(lPuestoResult,lDoctorResult,lPacienteResult,lSintomaResult,diagnositotxt,recetatxt);
        crud.getChildren().addAll(labels,ingresos);
        
        panel.getChildren().addAll(label,crud,botones,atras);//Vbox
        
        
    }
   
    private void FianlizarAtencion(){

        try {
            String puesto=this.lPuestoResult.getText();   
            String doctor=this.lDoctorResult.getText();
            String paciente=this.lPacienteResult.getText();
            String sintoma=this.lPuestoResult.getText();
            String diagnostico=this.diagnositotxt.getText();
            String receta=this.recetatxt.getText();
           
            if((diagnostico.isBlank()) || (receta.isBlank())){//para evitar repetir
                Controlador.crearAlerta("ERROR", "Debe tener el paciente un diagnostico y una receta"); 
            } else{
              Controlador.crearNotificacion("Confirmacion", "Paciente Atendido Correctamente");
               Controlador.agregarHistorial(Integer.parseInt(puesto),doctor,paciente,sintoma,diagnostico,receta);
               Controlador.eliminarAsignacionPuestoPaciente(puestoEscogido); //elimino al paciete del puesto 
               Controlador.asignarNuevoPaciente(puestoEscogido); //agrego nuevo paciente al puesto
               
             }
            
        } catch (NumberFormatException e) {
            Controlador.crearAlerta("EROR","ERROR!");
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
        atenderPaciente.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
        atras.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
              
        atenderPaciente.setPrefSize(150, 40);
        atras.setPrefSize(150, 40);
        
        atenderPaciente.setAlignment(Pos.CENTER);
        atras.setAlignment(Pos.CENTER);
        
    }
  
    private  void setearAcciones(Stage stage){
        atenderPaciente.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            
                            FianlizarAtencion();
                            Scene escena= new Scene(new PanelAtenderPaciente(stage).getRoot(),700,700);
                            stage.setTitle("Atender Pacientes");
                            stage.setScene(escena);                            
                            stage.show();       
                             
                            
			}
	});
       
        atras.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            
                            Scene escena= new Scene(new PanelAtenderPaciente(stage).getRoot(),700,700);
                            stage.setTitle("Atender Pacientes");
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

