/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PreloaderInitalProgress extends Preloader {

    private Stage preloaderStage;
    private Scene scene;
    
    public PreloaderInitalProgress() {
        
    }

    @Override
    public void init() throws Exception {               
                                         
    Parent root1 = FXMLLoader.load(getClass().getResource("/fxml/InicioFXML.fxml"));               
    scene = new Scene(root1);        
    
                
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
       this.preloaderStage = primaryStage;
       preloaderStage.getIcons().add(new Image("img/TOT-Icon.png"));

        // Set preloader scene and show stage.
        preloaderStage.setTitle("TOT Learning System - Client");
        ProgressBar progressBar = new ProgressBar(0);
        
        
        preloaderStage.setScene(scene);  
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();
        
        
      
    }

    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info) {
      
          //if (info instanceof Preloader.ProgressNotification) {
           // TableViewController.label.setText("Loading "+((Preloader.ProgressNotification) info).getProgress()*100 + "%");
            //System.out.println("Value@ :" + ((Preloader.ProgressNotification) info).getProgress());
        //    TableViewController.statProgressBar.setProgress(((Preloader.ProgressNotification) info).getProgress());
        //}

               
        
    }

    @Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info) {
      
        Preloader.StateChangeNotification.Type type = info.getType();
        switch (type) {
            
            case BEFORE_START:
                // Called after MyApplication#init and before MyApplication#start is called.
                System.out.println("BEFORE_START");
               preloaderStage.hide();
                break;
        }
        
        
    }
}

