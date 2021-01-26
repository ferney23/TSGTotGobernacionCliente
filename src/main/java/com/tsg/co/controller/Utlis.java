/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 *
 * @author Ferney
 */
public class Utlis {

    public static JFXDialog jdialogoLogin(String content, String title, StackPane stackPane) {
        // String title = "Inicio de Sesion";
        // String content = "Usuario incorrecto";
        JFXDialogLayout jFXDialogLayout = new JFXDialogLayout();
        jFXDialogLayout.setHeading(new Text(title));
        jFXDialogLayout.setBody(new Text(content));
        JFXButton close = new JFXButton("close");
        close.setButtonType(JFXButton.ButtonType.RAISED);
        jFXDialogLayout.setActions(close);

        JFXDialog jFXDialog = new JFXDialog(stackPane, jFXDialogLayout, JFXDialog.DialogTransition.BOTTOM);
        jFXDialog.setMaxSize(50, 100);
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                jFXDialog.close();
            }
        });
        jFXDialog.show();
        return jFXDialog;

    }

}
