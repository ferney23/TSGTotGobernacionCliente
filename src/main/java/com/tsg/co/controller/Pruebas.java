/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import com.sun.javafx.application.LauncherImpl;
import com.tsg.co.Sincronizacion.Inicio;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ferney
 */
public class Pruebas extends Application {

    private TableViewController tableViewController;
    private static final int COUNT_LIMIT = 10;

    @Override
    public void start(Stage stage) throws Exception {

        Stage stageMaterialesClases = new Stage();

        /*stage.getIcons().add(new Image("img/TOT-Icon.png"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/InicioFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("TOT Learning System - Client");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();*/
        /**
         * stage.getIcons().add(new Image("img/TOT-Icon.png")); Parent roote =
         * FXMLLoader.load(getClass().getResource("/fxml/InicioFXML.fxml"));
         * Scene scena = new Scene(roote); stage.setTitle("TOT Learning System -
         * Client"); stage.setResizable(false); stage.setScene(scena);
         * stage.show();
         *
         */
        //stage.close();
        stage.getIcons().add(new Image("img/TOT-Icon.png"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/TableView.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("TOT Learning System - Client");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stageMaterialesClases.close();

    }

    @Override
    public void init() throws Exception {

        Inicio inicio = new Inicio();

        inicio.run();
        //  System.out.println(inicio.getInicio() + "" + inicio.getFin());

    }

    public static void main(String[] args) {

        LauncherImpl.launchApplication(Pruebas.class, PreloaderInitalProgress.class, args);
    }

}
