package com.tsg.co.controller;

import com.sun.javafx.application.LauncherImpl;
import com.tsg.co.Sincronizacion.Inicio;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application {

    private Scene sceneInicioSesion;
    private Stage stageInicioSesion;
    private Inicio inicio;
    private InicioSesionFXMLController inicioSesionFXMLController;

    @Override
    public void start(Stage stage) throws Exception {

        this.stageInicioSesion = stage;
        this.stageInicioSesion.getIcons().add(new Image("img/TOT-Icon.png"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InicioSesionFXML.fxml"));
        this.sceneInicioSesion = new Scene((Pane) loader.load());
        this.stageInicioSesion.setScene(this.sceneInicioSesion);
        inicioSesionFXMLController = loader.<InicioSesionFXMLController>getController();
        inicioSesionFXMLController.setStageInicioSesion(this.stageInicioSesion);
        inicioSesionFXMLController.setSceneInicioSesion(this.sceneInicioSesion);
        inicioSesionFXMLController.setConectado(this.inicio.isConectado());
        this.inicioSesionFXMLController.setEnf(this.inicio.getEnf());
        this.inicioSesionFXMLController.setManager(this.inicio.getManager());
        this.stageInicioSesion.setTitle("TOT Learning System - Client");
        this.stageInicioSesion.setResizable(false);
        this.stageInicioSesion.show();
        this.stageInicioSesion.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override

            public void handle(WindowEvent event) {
                inicio.getEnf().close();
                Platform.exit();
                System.exit(0);
            }
        }
        );
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

        this.inicio = new Inicio();
        inicio.run();

        //  System.out.println(inicio.getInicio() + "" + inicio.getFin());
    }

    public static void main(String[] args) {

        LauncherImpl.launchApplication(App.class, PreloaderInitalProgress.class, args);
    }

    @Override
    public void stop() {

    }
}
