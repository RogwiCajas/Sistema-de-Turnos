/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadeturnos;

import controlador.Controlador;
import static controlador.Controlador.agregarPaciente;
import static controlador.Controlador.cargarMedicos;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modelo.Medico;
import pantallas.PanelAdministrar;
import pantallas.PanelPrincipal;

/**
 *
 * @author cajas
 */
public class SistemaDeTurnos extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
     
        System.out.println( agregarPaciente("Cristina", "Guerrero", "Masculino",23, "Covid 19"));
    }

   
    /*
    @Override
    public void start(Stage stage)  {
        
        
        stage.setTitle("Reproductor de video Java FX - Lista Circular");
        stage.setScene(new PanelPrincipal().getNewScene());
        stage.show();
    }
    */
    //Descomentar y comentar el start de arriba para probar paneles de administracion
    
    @Override
    public void start(Stage stage)  {
        Controlador.iniciarSistema();
        
        Scene scene = new Scene(new PanelAdministrar(stage).getRoot(),700,700);
        stage.setTitle("Menu Administrar");
        stage.setScene(scene);
        stage.show();
    }
    
    
}
