/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import javafx.application.Application;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

/**
 *
 * @author Ferney
 */
public class PantallaInicio extends Application {
    
    final Float[] values = new Float[] {-1.0f};
    final Label[] labels = new Label[values.length];
    final ProgressBar[] pbs = new ProgressBar[values.length];
    final ProgressIndicator[] pins = new ProgressIndicator[values.length];
    final HBox hbs[] = new HBox[values.length];

    
   @Override
    public void start(Stage stage) throws Exception {
        
        
        

        stage.getIcons().add(new Image("img/TOT-Icon.png"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/InicioFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("KOT Learning System - Client");
        stage.setResizable(false);
        stage.setScene(scene);
        
       
        
        /*
            final Label label = labels[0] = new Label();
            label.setText("progress:" + values[0]);
 
            final ProgressBar pb = pbs[0] = new ProgressBar();
            pb.setProgress(values[0]);
 
            final ProgressIndicator pin = pins[0] = new ProgressIndicator();
            pin.setProgress(values[0]);
            final HBox hb = hbs[0] = new HBox();
            hb.setSpacing(5);
            
            hb.setAlignment(Pos.CENTER);
            hb.getChildren().addAll(label, pb, pin);
        
 
        final VBox vb = new VBox();
        vb.setSpacing(5);
        vb.getChildren().addAll(hbs);
        scene.setRoot(vb);
        */
        //scene.setRoot();
        stage.show();

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
