/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 *
 * @author IsaacSolis
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
public class PanelAtenderPaciente extends Pane{
    private Label label;
    private Button atencionPuestos;
    private Button atras;
    
    protected static int puestoEscogido;
    
    //Tabla para mostrar Puestos
    private TableView<Puesto> tblpuestos;
    private TableColumn colIDM;
    private TableColumn colIDP;
    private TableColumn colIdPaciente;
 
    
    private ObservableList<Puesto> puestos;
     //atributo para mostrar tabla de puestos
    private HBox botones;
    private HBox crud;
    private VBox labels;
    private VBox ingresos;
    
    private VBox panel;
    private Pane root;
    
    public PanelAtenderPaciente(Stage stage){
        
        iniciar( stage);
    }
    private void iniciar(Stage stage){
        //Iniciamos Root y panel principal
        
        this.panel=new VBox(40);
        this.root=new StackPane(panel);
        
        //nodos a usar
        //nodos a usar
        this.label= new Label("Atencion de Pacientes");
        this.atencionPuestos= new Button("Ir a puesto");
         this.atras=new Button("Volver");
         
          //tabla
        cargarTabla();
        //formatos de los nodos
        formato();
        //acciones de los nodos
        setearAcciones(stage);
        //Llenamos el nodo root
        this.botones=new HBox(150);
        this.crud=new HBox(20);
        this.labels=new VBox(15);
        this.ingresos=new VBox(5);
        crud.getChildren().addAll(labels,ingresos);
        
        panel.getChildren().addAll(label,tblpuestos,botones,crud,atencionPuestos,atras);//Vbox
        
    }
   
     
    private void cargarTabla(){
        
        this.puestos=FXCollections.observableList(Controlador.getPuestos());//setea medicos en la tabla
              
        this.tblpuestos=new TableView<>();
        this.colIDP=new TableColumn("ID Puesto");
        this.colIDM=new TableColumn("Medico");
        this.colIdPaciente=new TableColumn("id Paciente");

               
        tblpuestos.getColumns().addAll(colIDP,colIDM,colIdPaciente);
        tblpuestos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        this.colIDP.setCellValueFactory(new PropertyValueFactory("id"));
        this.colIDM.setCellValueFactory(new PropertyValueFactory("doctor"));
        this.colIdPaciente.setCellValueFactory(new PropertyValueFactory("paciente"));
        
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
        atencionPuestos.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
         atras.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
               
        atencionPuestos.setPrefSize(150, 40);
         atras.setPrefSize(150, 40);
         
        atencionPuestos.setAlignment(Pos.CENTER);
         atras.setAlignment(Pos.CENTER);
         
    }
    private void guardarSelecion(){
          Puesto p=this.tblpuestos.getSelectionModel().getSelectedItem();
          puestoEscogido = p.getId();
     }
    private  void setearAcciones(Stage stage){
        atencionPuestos.setOnAction( new EventHandler<ActionEvent>() {
                        @Override
			public void handle(ActionEvent t) {
                            guardarSelecion();                          
                            Scene escena= new Scene(new PanelAtencion(stage).getRoot(),700,700);
                            stage.setScene(escena);
                            stage.show();
                            
                            
			}
	});
        
        atras.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            //setea el root a la ventana siguiente
                            System.out.println("Presiono salir");
                            Scene escena= new Scene(new PanelPaciente(stage).getRoot(),700,700);
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

