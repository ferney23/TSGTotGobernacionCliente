/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.Sincronizacion;

import org.json.JSONObject;

/**
 *
 * @author Ferney
 */
public class TestInicio {

    public static void main(String[] args) throws Exception {
        Inicio inicio = new Inicio();

        JSONObject obArchivos = new JSONObject();
        obArchivos.put("username", "estudiante.uno");
        obArchivos.put("password", "tsg123");

        System.out.println(obArchivos);
        //obArchivos.put("EstudianteId", entrega.getEntrega().getEstudiante().getIdEstudiante());
        //   aux ="";
        String login = inicio.peticionHttpPostIniciarSesion("http://localhost:6104/api/cuentas/login", obArchivos);
        JSONObject jsonResp = new JSONObject(login);
        System.out.println(jsonResp.getString("token"));
        
        String aux = inicio.peticionHttpGetArray("http://localhost:6104/api/TareaRegistro/misTareasPendientesPorDescargar",jsonResp.getString("token"));
        System.out.println(aux);
        //  inicio.run();
    }

}
