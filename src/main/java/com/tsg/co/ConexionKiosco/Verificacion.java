/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.ConexionKiosco;

import java.net.Socket;

/**
 *
 * @author Ferney
 */
public class Verificacion {

    public boolean Verificar(String dirWeb, int puerto) {
        boolean verf = false;
        try {
            Socket s = new Socket(dirWeb, puerto);
            if (s.isConnected()) {
                verf = true;
                System.out.println("Conexión establecida con la dirección: " + dirWeb + " a travéz del puerto: " + puerto);
            }
        } catch (Exception e) {
            System.err.println("No se pudo establecer conexión con: " + dirWeb + " a travez del puerto: " + puerto);
        }
        return verf;
    }

}
