
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

public class PanelPaciente extends Pane{
    private Button  atenderPaciente;
    private Button  crearPaciente;
    private Button  atras;
    private Label label;
    
    
    private VBox panel;
    private Pane root;
    /**
     * Crea la vista menu administrar
     */
    public PanelPaciente(Stage stage){
        iniciar(stage);
        
        
        
    }
    private  void iniciar(Stage stage){
        //Iniciamos Root y panel principal
        
        this.panel=new VBox(40);
        this.root=new StackPane(panel);
        
        //nodos a usar
        this.label= new Label("ADMINISTRAR PACIENTES");
        this.atenderPaciente= new Button("Atender Paciente");
        this.crearPaciente=new Button("Crear Paciente");
        this.atras=new Button("Volver");
        //formatos de los nodos
        formato();
        //acciones de los nodos
        setearAcciones(stage);
        //Llenamos el nodo root
        panel.getChildren().addAll(label,atenderPaciente,crearPaciente,atras);
        
    }
    private void formato(){
        //formato y alineacion del panel principal
        panel.setPadding(new Insets(150,70,70,100));
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-background-color: Beige");
        
        //formato titulo
   
        label.setStyle("-fx-text-fill: Black; -fx-font-size: 35; -fx-font-weight: BOLD; -fx-background-color: Blue");
        label.setAlignment(Pos.CENTER);
        label.setLayoutX(150);
        label.setLayoutY(80);
        
        //Formato botones
        atenderPaciente.setStyle("-fx-background-color: Cyan; -fx-text-fill: Black;");
        crearPaciente.setStyle("-fx-background-color: Cyan; -fx-text-fill: Black;");
        atras.setStyle("-fx-background-color: Cyan; -fx-text-fill: Black;");
        
        
        atenderPaciente.setPrefSize(150, 40);
        crearPaciente.setPrefSize(150, 40);
        atras.setPrefSize(150, 40);
        
        atenderPaciente.setAlignment(Pos.CENTER);
        crearPaciente.setAlignment(Pos.CENTER);
        atras.setAlignment(Pos.CENTER);
        
    }
    private  void setearAcciones(Stage stage){
        atenderPaciente.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            //aqio ira para crear pacientes
                            Scene  escena=new Scene(new PanelAtenderPaciente(stage).getRoot(),700,700);
                            stage.setTitle("Atender Pacientes");
                            stage.setScene(escena);
                            stage.show();
			}
	});
        crearPaciente.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                                                        //aqio ira para crear pacientes
                            Scene escena=new Scene(new PanelCrearPaciente(stage).getRoot(),700,700);
                            stage.setTitle("Atender Pacientes");
                            stage.setScene(escena);
                            stage.show();
			}
	});
        atras.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                            //Setear aqui cambio a la Pantalla de menus prinicpal
                            System.out.println("Agregar Paciente");
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


