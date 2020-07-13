/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

/**
 *
 * @author cajas
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelAdministrar extends Pane{
    private Button  puestos;
    private Button  doctores;
    private Button  atras;
    private Label label;
    
    
    private VBox panel;
    private Pane root;
    /**
     * Crea la vista menu administrar
     */
    public PanelAdministrar(Stage stage){
        iniciar(stage);
        
        
        
    }
    private  void iniciar(Stage stage){
        //Iniciamos Root y panel principal
        
        this.panel=new VBox(40);
        this.root=new StackPane(panel);
        
        //nodos a usar
        this.label= new Label("ADMINISTRAR");
        this.puestos= new Button("Administrar Puestos");
        this.doctores=new Button("Administrar Doctores");
        this.atras=new Button("Volver");
        //formatos de los nodos
        formato();
        //acciones de los nodos
        setearAcciones(stage);
        //Llenamos el nodo root
        panel.getChildren().addAll(label,puestos,doctores,atras);
        
    }
    private void formato(){
        //formato y alineacion del panel principal
        panel.setPadding(new Insets(150,70,70,100));
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-background-color: Beige");
        
        //formato titulo
    
        label.setStyle("-fx-text-fill: Black; -fx-font-size: 35; -fx-font-weight: BOLD; -fx-background-color: Cyan");
        label.setAlignment(Pos.CENTER);
        label.setLayoutX(150);
        label.setLayoutY(80);
        
        //Formato botones
        puestos.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
        doctores.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
        atras.setStyle("-fx-background-color: Blue; -fx-text-fill: White;");
        
        
        puestos.setPrefSize(150, 40);
        doctores.setPrefSize(150, 40);
        atras.setPrefSize(150, 40);
        
        puestos.setAlignment(Pos.CENTER);
        doctores.setAlignment(Pos.CENTER);
        atras.setAlignment(Pos.CENTER);
        
    }
    private  void setearAcciones(Stage stage){
        puestos.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            
                            Scene  escena=new Scene(new PanelAdministrarPuestos(stage).getRoot(),700,700);
                            stage.setTitle("Administrar Puestos");
                            stage.setScene(escena);
                            stage.show();
			}
	});
        doctores.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            
                            Scene escena=new Scene(new PanelAdministrarDoctores(stage).getRoot(),700,700);
                            stage.setTitle("Administrar Doctores");
                            stage.setScene(escena);
                            stage.show();
			}
	});
        atras.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            //Setear aqui cambio a la Pantalla de menus prinicpal
                            System.out.println("Presiono salir");
                            stage.close();
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


