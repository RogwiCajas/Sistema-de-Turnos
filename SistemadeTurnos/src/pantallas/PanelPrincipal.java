/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import controlador.Controlador;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import tda.CircularDoublyLinkedList;
import tda.Node;

/**
 *
 * @author Alejandra Quimi
 */
public class PanelPrincipal {
    private CircularDoublyLinkedList<String> l;
    private MediaView mv;
    private Scene NewScene;
    private HBox root;
    MediaPlayer mediaPlayer;
    Pane panel;
    

    public Scene getNewScene() {
        return NewScene;
    }

  
   
    public PanelPrincipal() {
        Controlador.llenarListaCircular(System.getProperty("user.dir"));
        l=Controlador.getVideos();
        reproducirListaVideos();

        mv=new MediaView();
        //mv.setTranslateY(30);
        mv.setFitHeight(360.0);
        mv.setFitWidth(800.0);
        
        panel=new Pane(mv);
        panel.setMinSize(880.0,365.0);
        root=new HBox(10);
        root.getChildren().addAll(panel);
        NewScene=new Scene(root,1200,365.0);
   
        
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
