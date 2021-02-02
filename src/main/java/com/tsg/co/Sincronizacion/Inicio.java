/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.Sincronizacion;

import com.tsg.co.ConexionKiosco.Verificacion;
import com.tsg.co.Persistencia.ManejoDatos;
import com.tsg.co.Persistencia.ObtenerDatos;
import com.tsg.co.controller.ClasesMaterialController;
import com.tsg.co.controller.InicioFXMLController;
import com.tsg.co.model.AchivosTot;
import com.tsg.co.model.Clases;
import com.tsg.co.model.Entregas;
import com.tsg.co.model.Estudiante;
import com.tsg.co.model.MaterialEstudio;
import com.tsg.co.model.Materias;
import com.tsg.co.model.Tareas;
import com.tsg.co.model.Usuario;
import com.tsg.co.model.Version;
import com.tsg.co.model.Kiosko;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;

/**
 *
 * @author Ferney
 */
public class Inicio implements Runnable {

    private EntityManagerFactory enf = Persistence.createEntityManagerFactory("tsg");
    //  private EntityManager manager = enf.createEntityManager(); 
    private ObtenerDatos obtenerDatos;
    private ManejoDatos manejoDatos;
    private Verificacion verificacion;
    private Estudiante estudianteCliente;
    private Long TareaRegistro;
    private Long fin;
    private double tiempo;
    private Long inicioTiempo;
    private boolean conectado;

    /**
     *
     */
    public Inicio() {

        this.obtenerDatos = new ObtenerDatos(this);
        this.verificacion = new Verificacion();
        this.manejoDatos = new ManejoDatos();
        this.obtenerDatos.setEnf(enf);
        this.manejoDatos.setEnf(enf);

    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public EntityManagerFactory getEnf() {
        return enf;
    }

    public void setEnf(EntityManagerFactory enf) {
        this.enf = enf;
    }

    @Override
    public void run() {
        //  manager = enf.createEntityManager();
        this.inicioTiempo = System.currentTimeMillis();

        try {

            String GetAddress = getMacAddress();

            System.err.println(GetAddress);
            obtenerDatos.KioscosListos();
            EntityManager manager = enf.createEntityManager();
            List<Kiosko> kioskos = manager.createQuery("FROM Kiosko").getResultList();
            manager.close();
            // boolean conectado;
            boolean saber = false;
            obtenerDatos.IniciarVersion();

            while (!saber) {
                for (int i = 0; i < kioskos.size(); i++) {
                    conectado = verificacion.Verificar(kioskos.get(i).getIP(), Integer.parseInt(kioskos.get(i).getPORT()));
                    //JOptionPane.showMessageDialog(null, "Iniciando  la sincronizacion");

                    if (conectado == true) {
                        String ip = kioskos.get(i).getIP() + ":" + kioskos.get(i).getPORT();
                        try {

                            String version = "http://" + ip + "/api/Versiones";
                            String version1 = this.peticionHttpGet(version);
                            JSONObject obj1 = new JSONObject(version1);
                            float versionnube = (float) obj1.getDouble("numero");
                            EntityManager manageres = enf.createEntityManager();
                            Version versionBases = manageres.find(Version.class, 1L);
                            manageres.close();
                            float versionactual = (float) versionBases.getNumero();
                            System.out.println("version actual" + versionactual + "version kisoko" + versionnube);
                            if (versionactual == versionnube) {
                                //  System.out.println("la misma");
                            } else {

                                List<Usuario> usuariosRecorrer = manejoDatos.listarUsuarios();

                                for (Usuario usuarioRecorrer : usuariosRecorrer) {
                                    //this.estudianteCliente = estudianteRecorrer;
                                    JSONObject objectUsuarios = new JSONObject();
                                    objectUsuarios.put("username", usuarioRecorrer.getUsername());
                                    objectUsuarios.put("password", usuarioRecorrer.getPassword());

                                    String login = "http://" + ip + "/api/cuentas/login";
                                    System.out.println();

                                    String loginEndPoint = this.peticionHttpPostIniciarSesion(login, objectUsuarios);
                                    System.out.println(loginEndPoint + "respuesta");
                                    if (!loginEndPoint.equals("")) {
                                        JSONObject jsonResp = new JSONObject(loginEndPoint);
                                        String identificacion = "http://" + ip + "/api/Estudiantes/miidentificacion";

                                        String identificacionEndPoint = this.peticionHttpGetToken(identificacion, jsonResp.getString("token"));
                                        System.out.println(identificacionEndPoint);
                                        JSONObject jSONObjectidentificacion = new JSONObject(identificacionEndPoint);
                                        this.estudianteCliente = this.obtenerDatos.actualizarEstudiante(jSONObjectidentificacion, usuarioRecorrer);

                                        String materiasEstudianteEndPointUrl = "http://" + ip + "/api/materias/mismaterias";
                                        String materiasEstudianteEnd = this.peticionHttpGetArray(materiasEstudianteEndPointUrl, jsonResp.getString("token"));

                                        JSONArray jsonArrayMaterias = new JSONArray(materiasEstudianteEnd);

                                        Set<Materias> arrayListMaterias = obtenerDatos.AddEstudianteMaterias(jsonArrayMaterias, this.estudianteCliente);

                                        // JSONObject jSONObjectMaterias = (JSONObject) jsonArrayMaterias.get(jM);
                                        String endpointTareas = "http://" + ip + "/api/TareaRegistro/misTareasPendientesPorDescargar";
                                        String tareasEndPointUrl = this.peticionHttpGetArray(endpointTareas, jsonResp.getString("token"));
                                        if (!tareasEndPointUrl.equals("")) {
                                            JSONArray jSONArrayTareas = new JSONArray(tareasEndPointUrl);
                                            for (int jt = 0; jt < jSONArrayTareas.length(); jt++) {
                                                JSONObject jSONObjectTareasRegistrar = (JSONObject) jSONArrayTareas.get(jt);
                                                String registroTarea = this.CrearRegistroTarea(ip, jSONObjectTareasRegistrar.getLong("tareaId"), jsonResp.getString("token"));

                                                JSONObject jSONObjectTareasRegistro = new JSONObject(registroTarea);
                                                this.obtenerDatos.actualizarTareas(jSONObjectTareasRegistrar, ip, estudianteCliente, jSONObjectTareasRegistro.getLong("id"));

                                                obtenerDatos.updateBlobTareas(jSONObjectTareasRegistrar, ip, estudianteCliente);

                                            }
                                        }

                                        String endpointClases = "http://" + ip + "/api/clases/misClases";
                                        String clasesEndPointUrl = this.peticionHttpGetArray(endpointClases, jsonResp.getString("token"));

                                        JSONArray jsonArrayClases = new JSONArray(clasesEndPointUrl);

                                        this.obtenerDatos.actualizarClases(jsonArrayClases);

                                        String endpointMaterialEstudio = "http://" + ip + "/api/MaterialEstudioRegistros/misMaterialesPendientesPorDescargar";
                                        String materialEstudioEndPointUrl = this.peticionHttpGetArray(endpointMaterialEstudio, jsonResp.getString("token"));
                                        System.out.println(materialEstudioEndPointUrl);

                                        if (!materialEstudioEndPointUrl.equals("")) {
                                            JSONArray jsonArrayMaterialEstudio = new JSONArray(materialEstudioEndPointUrl);
                                            this.obtenerDatos.actualizarMaterialEstudio(jsonArrayMaterialEstudio);
                                        }

                                        // 
                                        obtenerDatos.postArchivos(ip, jsonResp.getString("token"), this.estudianteCliente);
                                    }
                                }

                            }

                        } catch (Exception ex) {
                            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        //  JOptionPane
                     //   JOptionPane.showMessageDialog(null, "Sincronizacion Completa ");
                        System.out.println("Se termino la sincronizacion");

                    } else {
                      //  JOptionPane.showMessageDialog(null, "No se pudo realizar la sincronizacion \n No esta conectado al kiosco");

                    }
                }

                saber = true;
            }

        } catch (SocketException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.fin = System.currentTimeMillis();

        this.tiempo = (double) ((fin - inicioTiempo) / 1000);

        System.out.println(tiempo + " segundos");
    }

    public String peticionHttpGetArray(String urlParaVisitar, String token) throws Exception {
        String cap = "";
        String bearer = "Bearer ";

        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (GeneralSecurityException e) {
        }
// Now you can access an https URL without having the certificate in the truststore
        try {

            URL url = new URL(urlParaVisitar);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json; charset=UTF-8");
            conn.addRequestProperty("Authorization", bearer + token);
            System.out.println(conn.getResponseCode());
            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;

                while ((output = br.readLine()) != null) {

                    cap = cap.concat(output);
                }

                conn.disconnect();
            } else {
                conn.disconnect();
                return cap;
            }

        } catch (MalformedURLException e) {
        }

        return cap;

    }

    public String peticionHttpGetToken(String urlParaVisitar, String token) throws Exception {
        String cap = "";
        String bearer = "Bearer ";

        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (GeneralSecurityException e) {
        }
// Now you can access an https URL without having the certificate in the truststore
        try {

            URL url = new URL(urlParaVisitar);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json; charset=UTF-8");
            conn.addRequestProperty("Authorization", bearer + token);

            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;

                while ((output = br.readLine()) != null) {
                    output = output.replace("[", "").replace("]", "");
                    cap = cap.concat(output);
                }

                conn.disconnect();
            } else {
                conn.disconnect();
                return cap;
            }

        } catch (MalformedURLException e) {
        }

        return cap;

    }

    /**
     *
     * @param urlParaVisitar
     * @param token
     * @return
     * @throws Exception
     */
    public String peticionHttpGet(String urlParaVisitar) throws Exception {
        String cap = "";

        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (GeneralSecurityException e) {
        }
// Now you can access an https URL without having the certificate in the truststore
        try {

            URL url = new URL(urlParaVisitar);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json; charset=UTF-8");

            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;

                while ((output = br.readLine()) != null) {
                    output = output.replace("[", "").replace("]", "");
                    cap = cap.concat(output);
                }

                conn.disconnect();
            } else {
                conn.disconnect();
                return cap;
            }

        } catch (MalformedURLException e) {
        }

        return cap;

    }

    public static String getMacAddress() throws UnknownHostException,
            SocketException, Exception {
        InetAddress ipAddress = InetAddress.getLocalHost();
        NetworkInterface networkInterface = NetworkInterface
                .getByInetAddress(ipAddress);
        byte[] macAddressBytes = networkInterface.getHardwareAddress();
        StringBuilder macAddressBuilder = new StringBuilder();
        if (macAddressBytes != null) {
            for (int macAddressByteIndex = 0; macAddressByteIndex < macAddressBytes.length; macAddressByteIndex++) {
                String macAddressHexByte = String.format("%02X",
                        macAddressBytes[macAddressByteIndex]);
                macAddressBuilder.append(macAddressHexByte);

                if (macAddressByteIndex != macAddressBytes.length - 1) {
                    macAddressBuilder.append(":");
                }
            }
        }
        return macAddressBuilder.toString();
    }

    /**
     *
     * @param urlTopost
     * @param json
     * @return
     * @throws Exception
     */
    public String peticionHttpPost(String urlTopost, JSONObject json, String token) throws Exception {
        String bien = "";
        String bearer = "Bearer ";

        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (GeneralSecurityException e) {
        }

        try {

            URL url = new URL(urlTopost);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.addRequestProperty("Authorization", bearer + token);
            String input = json.toString();
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            //System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                bien = bien.concat(output);
            }
            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return bien;
    }

    public String peticionHttpPostIniciarSesion(String urlTopost, JSONObject json) throws Exception {
        String bien = "";

        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (GeneralSecurityException e) {
        }

        try {

            URL url = new URL(urlTopost);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            String input = json.toString();
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                String output;

                while ((output = br.readLine()) != null) {
                    bien = bien.concat(output);
                }
                conn.disconnect();
            }

            //System.out.println("Output from Server .... \n");
        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return bien;
    }

    /**
     *
     * @param URL
     * @param json
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException No tocar
     */
    public boolean sendhttppostwhitfileProbar(String URL, JSONObject json) throws UnsupportedEncodingException, IOException {

        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (GeneralSecurityException e) {
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(URL);
        boolean si = false;
        FileBody bin = new FileBody(new File(json.getString("file")));
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
        reqEntity.addPart("file", bin);
        StringBody id = new StringBody(String.valueOf(json.getInt("entrega")), ContentType.DEFAULT_TEXT);
        reqEntity.addPart("entrega_id", id);
        reqEntity.addPart("upp", new StringBody(String.valueOf(0), ContentType.DEFAULT_TEXT));
        reqEntity.addPart("codigo", new StringBody(String.valueOf(json.getInt("codigo")), ContentType.DEFAULT_TEXT));
        reqEntity.addPart("subida", new StringBody(String.valueOf(json.getInt("subida")), ContentType.DEFAULT_TEXT));
        HttpEntity entity = reqEntity.build();
        httppost.setEntity(entity);
        HttpResponse httpresponse = httpClient.execute(httppost);

        return si;
    }

    public boolean sendhttppostwhitfile(String URL, JSONObject json, String token) throws UnsupportedEncodingException, IOException {

        String bearer = "Bearer ";

        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (GeneralSecurityException e) {
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        System.out.println(URL);
        HttpPost httppost = new HttpPost(URL);

        System.out.println(httppost);
        boolean si = false;

        FileBody bin = new FileBody(new File(json.getString("File")));
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();

        reqEntity.addPart("File", bin);
        //  StringBody id = new StringBody(String.valueOf(json.getInt("Entrega_id")), ContentType.DEFAULT_TEXT);

        // reqEntity.addPart("Entrega_id", id);
        //reqEntity.addPart("upp", new StringBody(String.valueOf(json.getInt("Subida_id")), ContentType.DEFAULT_TEXT));
        reqEntity.addPart("MAC", new StringBody(String.valueOf(json.getString("MAC")), ContentType.DEFAULT_TEXT));
        reqEntity.addPart("TareaRegistroId", new StringBody(String.valueOf(json.getInt("TareaRegistroId")), ContentType.DEFAULT_TEXT));

        HttpEntity entity = reqEntity.build();
        httppost.setEntity(entity);
        httppost.setHeader("Authorization", bearer + token);

        HttpResponse httpresponse = httpClient.execute(httppost);

        System.out.println(httpresponse.getCode());

        return si;
    }

    public Long getTareaRegistro() {
        return TareaRegistro;
    }

    public void setTareaRegistro(Long TareaRegistro) {
        this.TareaRegistro = TareaRegistro;
    }

    public Long getFin() {
        return fin;
    }

    public void setFin(Long fin) {
        this.fin = fin;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public String CrearRegistroTarea(String ip, Long tareaid, String token) throws Exception {
        String crearRegistro = "http://" + ip + "/api/tarearegistro/RegistrarMiTareaDescargada";
        JSONObject objectCrearRegistro = new JSONObject();
        objectCrearRegistro.put("tareaId", tareaid);
        // objectCrearRegistro.put("materiaId", materiaid);
        // objectCrearRegistro.put("estudianteId", estudianteId);
        //System.out.println(inicio.peticionHttpPost(urlEntrega, obEntregas));
        String respuesta = peticionHttpPost(crearRegistro, objectCrearRegistro, token);
        System.out.println("respuesta " + respuesta);
        return respuesta;

    }

}
