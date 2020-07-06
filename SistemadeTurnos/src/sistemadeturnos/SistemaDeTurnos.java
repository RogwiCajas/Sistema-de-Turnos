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

/**
 *
 * @author cajas
 */
public class SistemaDeTurnos extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        System.out.println( agregarPaciente("Cristina", "Guerrero", "Masculino",23, "Covid 19"));
    }

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    
}
