package com.tsg.co.controller;

import com.sun.javafx.application.LauncherImpl;
import com.tsg.co.Sincronizacion.Inicio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.getIcons().add(new Image("img/TOT-Icon.png"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InicioSesionFXML.fxml"));
        stage.setScene(new Scene((Pane) loader.load()));
        InicioSesionFXMLController inicioFXMLController = loader.<InicioSesionFXMLController>getController();
        inicioFXMLController.setStageInicio(stage);

        stage.setTitle("TOT Learning System - Client");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    @Override
    public void init() throws Exception {

        Inicio inicio = new Inicio();

        inicio.run();
        //  System.out.println(inicio.getInicio() + "" + inicio.getFin());

    }

    public static void main(String[] args) {

        LauncherImpl.launchApplication(App.class, PreloaderInitalProgress.class, args);
    }

}
