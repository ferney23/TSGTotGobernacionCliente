package com.tsg.co.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXProgressBar;
import com.tsg.co.Sincronizacion.Inicio;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ferney
 */
public class InicioFXMLController implements Initializable {

    Task tareas;

    @FXML
    private AnchorPane paneMenu;
    @FXML
    private ProgressBar initialProgress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initialProgress.setProgress(0);
        tareas = CrearTarea();
        initialProgress.progressProperty().unbind();
        initialProgress.progressProperty().bind(tareas.progressProperty());

        tareas.messageProperty().addListener(
                new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            }

        });
        new Thread(tareas).start();
      

    }

    public Task CrearTarea() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println(".call()");
                for (int i = 0; i < 100; i++) {
                    //inicio.run();
                    Thread.sleep(1000);
                    updateMessage("Tarea Iniciada");
                    updateProgress(i + 1, 100);
                    // inicio.run();

                    if (i == 100) {
                        System.out.println("Tarea Terminada");
                    }
                }

                return true;

            }
        };
    }

}
