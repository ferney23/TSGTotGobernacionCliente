/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.test;

import static com.tsg.co.Persistencia.ObtenerDatos.reemplazar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ferney
 */
public class TestTildes {

    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println("Ferney Ni?o ");
        System.out.println("Ferney Niño ");

        String baseUrl = "https://www.google.com/search?q=";

        String query = "Hellö Wörld@Java";
        String encodedQuery = encodeValue(query); // Encoding a query string

        String completeUrl = baseUrl + encodedQuery;
        System.out.println(completeUrl);

        
        
        System.out.println("¡Hola pap\u00e1!\nYa puedo escribir bien.\n\u00d1a\u00f1a\u00f1a\u00f1a");
        String urls = "http://localhost:6104/media/Material_Estudio/6606 tsgprueba/6760 Biologia/3231 célula y sus partes/la célula y sus partes.pdf";
        System.err.println(urls);
        try {
            URL url = new URL(urls);
            //URL url = new URL("file:/E:/Program Files/IBM/SDP/runtimes/base");
           // URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
          //  String utf= uri.toASCIIString();
           // URL urlfinal = new URL(utf);  
           // System.out.println(urlfinal);
             try (InputStream in = url.openStream();
                                BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                            //Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
                        }       
                    

        } catch (MalformedURLException ex) {
            Logger.getLogger(TestTildes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
