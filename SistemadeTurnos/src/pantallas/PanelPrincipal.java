/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import controlador.Controlador;
import java.io.File;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import modelo.Puesto;
import tda.CircularDoublyLinkedList;
import tda.Node;

/**
 *
 * @author Alejandra Quimi
 */
public class PanelPrincipal {
    private CircularDoublyLinkedList<String> l;
    private LinkedList<Integer> turnos;
    private MediaView mv;
    private Scene NewScene;
    private HBox video_puestos;
    private VBox turnos_puestos;
    private HBox root;
    private Label turno;
    private Label puesto;
    MediaPlayer mediaPlayer;
    Pane panel;
    VBox caja;
    Label titulo;
    Button administrar;
    Button atender_puestos;
    

    public Scene getNewScene() {
        return NewScene;
    }

  
   
    public PanelPrincipal() {
        Controlador.llenarListaCircular(System.getProperty("user.dir"));
        l=Controlador.getVideos();
        reproducirListaVideos();

        mv=new MediaView();
        
        mv.setFitHeight(370.0);
        mv.setFitWidth(650.0);
        
        panel=new Pane(mv);
        panel.setMinSize(650.0,370.0);
        
        video_puestos=new HBox(5);
        
        video_puestos.getChildren().addAll(panel);
        
        llenar_pantallar();
        root=new HBox(10);
        root.getChildren().addAll(video_puestos);
        iniciar();
        NewScene=new Scene(root,1200,365.0);
   
        
    }
    private void llenar_turnos(){
        turnos=new LinkedList<>();
        int i=1;
        LinkedList<Puesto> puesto=Controlador.getPuestos();
        System.out.println("tamaño "+puesto.size());
        while(i<=puesto.size()){
            System.out.println("valor de i "+i);
            turnos.add(i++);
           
        }
        
    }
     private void llenar_pantallar(){
        turno=new Label("TURNO");
        cambiar_labelprimervbox(turno);
        puesto=new Label("PUESTO");
        cambiar_labelsegundovbox(puesto);
        HBox s=new HBox(5);
        s.getChildren().addAll(turno,puesto);
        llenar_turnos();
        this.turnos_puestos=new VBox(5);
        turnos_puestos.getChildren().add(s);
        video_puestos.getChildren().add(turnos_puestos);
        
         
        //V.getChildren().clear();
        LinkedList<Puesto> puesto=Controlador.getPuestos();
        for(int i=0;i<puesto.size();i++){
           // V.getChildren().clear();

            HBox ho=new HBox(5);
            Label label=new Label("A"+String.valueOf(turnos.get(i)));
            cambiar_labelprimervbox(label);
            
            Label label2 = new Label(String.valueOf(puesto.get(i).getId()));
            cambiar_labelsegundovbox(label2);
            ho.getChildren().addAll(label,label2);
            turnos_puestos.getChildren().add(ho);
            
            
        }
        
        
        
    }
     private void cambiar_labelprimervbox(Label label){
         
        label.setFont(label.getFont().font(21));
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color: blue;");
        label.setMinSize(120,50);
        
    }
     private void cambiar_labelsegundovbox(Label label){
         label.setFont(label.getFont().font(21));
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color: brown;");
        label.setMinSize(120,50);
       
    }
    
    
    
    private  void iniciar(){
        
        
        this.caja=new VBox(10);
        caja.setAlignment(Pos.CENTER);
        caja.setTranslateX(90);
        caja.setTranslateY(-50);
        root.getChildren().add(caja);
        this.titulo= new Label("Menu");
        this.administrar= new Button("Administrar Paciente");
        this.atender_puestos=new Button("Administrar Puestos y Doctores");
        
        estilo();
        setearAcciones();
        
        caja.getChildren().addAll(titulo,administrar,atender_puestos);
        
    }
    private void estilo(){
        
   
        titulo.setStyle("-fx-text-fill: Black; -fx-font-size: 35");
        titulo.setAlignment(Pos.CENTER);
        titulo.setLayoutX(150);
        titulo.setLayoutY(80);
        administrar.setPrefSize(150, 40);
        
        atender_puestos.setPrefSize(197, 40);
        
        administrar.setAlignment(Pos.CENTER);
        
        atender_puestos.setAlignment(Pos.CENTER);
        
    }
    private  void setearAcciones(){
        administrar.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                           
                            Stage second_stage = new Stage();
                          
                            Scene  escena=new Scene(new PanelPaciente(second_stage).getRoot(),700,700);
                            second_stage.setTitle("Administrar Pacientes");
                            second_stage.setScene(escena);
                            second_stage.show();
			}
	});
        atender_puestos.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
                                                       
                            Stage second_stage = new Stage();                            
                            Scene escena=new Scene(new PanelAdministrar(second_stage).getRoot(),700,700);
                            second_stage.setTitle("Administrar Puestos y Doctores");
                            second_stage.setScene(escena);
                            second_stage.show();
			}
	});
        
       
    }
    
    
    
     /**
     * Funcion privada que se encarga de reproducir cada video recorriendo los nodos de la lista circular doble.Usamos hilos
     * 
     */
        private void reproducirListaVideos() {     
        Thread hilo = new Thread() {
            @Override
            public void run() {
                try {
                   
                   Node<String> actual=l.getprimero();
                   
                    while (true) {
                        
                        File file = new File(actual.getData().toString());
                        Media media = new Media(file.toURI().toString());
                        mediaPlayer = new MediaPlayer(media);
                        mv.setMediaPlayer(mediaPlayer);
                        mediaPlayer.play();
                        while (true) {
                            if (mediaPlayer.bufferProgressTimeProperty().getValue() != null) {
                                if (mediaPlayer.getCurrentTime().toString().equals(mediaPlayer.bufferProgressTimeProperty().getValue().toString())) {
                                    mediaPlayer.stop();
                                    break;
                                }
                            }
                            this.sleep(5000);

                        }
                       actual = actual.getNext();
                      
                    }
                } catch (InterruptedException ex) {
                }
            }
        };
        hilo.start();
    }    
    
    
}
