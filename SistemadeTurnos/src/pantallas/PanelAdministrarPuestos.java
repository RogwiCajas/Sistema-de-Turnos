/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cajas
 */
public class PanelAdministrarPuestos extends Pane{
    private Label label;
    private Button crearPuesto;
    private Button eliminarPuesto;
    private Button atras;
    //atributo para mostrar tabla de puestos
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
        this.label= new Label("ADMINISTRAR PUESTOS");
        this.crearPuesto= new Button("Crear Puesto");
        this.eliminarPuesto=new Button("Eliminar Puesto");
        this.atras=new Button("Volver");
        //formatos de los nodos
        formato();
        //acciones de los nodos
        setearAcciones(stage);
        //Llenamos el nodo root
        panel.getChildren().addAll(label,crearPuesto,eliminarPuesto,atras);
        
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
              
        crearPuesto.setPrefSize(150, 40);
        eliminarPuesto.setPrefSize(150, 40);
        atras.setPrefSize(150, 40);
        
        crearPuesto.setAlignment(Pos.CENTER);
        eliminarPuesto.setAlignment(Pos.CENTER);
        atras.setAlignment(Pos.CENTER);
        
    }
    private  void setearAcciones(Stage stage){
        crearPuesto.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            //setea el root a la ventana siguiente
                            System.out.println("Guarda puesto");
			}
	});
        eliminarPuesto.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            //setea el root a la ventana siguiente
                            System.out.println("elimina puesto");
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
