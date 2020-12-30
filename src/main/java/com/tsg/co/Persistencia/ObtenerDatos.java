/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.Persistencia;

import static com.ibm.icu.text.PluralRules.Operand.n;
import com.tsg.co.Sincronizacion.Inicio;
import com.tsg.co.model.AchivosTot;
import com.tsg.co.model.Clases;
import com.tsg.co.model.Entregas;
import com.tsg.co.model.Estudiante;
import com.tsg.co.model.InfoGrado;
import com.tsg.co.model.MaterialEstudio;
import com.tsg.co.model.Materias;
import com.tsg.co.model.Subida;
import com.tsg.co.model.Tareas;
import com.tsg.co.model.Usuario;
import com.tsg.co.model.Version;
import com.tsg.model.Kiosko;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import static java.net.URLEncoder.encode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Ferney
 */
public class ObtenerDatos {

    private Inicio inicio;
    private static EntityManager manager;
    private static EntityManagerFactory enf;
    private boolean nuevoCliente;

    public ObtenerDatos() {
        this.nuevoCliente = false;
    }

    public ObtenerDatos(Inicio inicio) {
        this.inicio = inicio;
    }

    public Inicio getInicio() {
        return inicio;
    }

    public void setInicio(Inicio inicio) {
        this.inicio = inicio;
    }

    public Version IniciarVersion() {
        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();

        Version auxVersion = null;
        Version existeVersion = null;

        try {
            existeVersion = (Version) manager.createQuery("SELECT ma FROM Version ma WHERE ma.idVersion = :id").setParameter("id", 1L).getSingleResult();

        } catch (Exception e) {
        }

        if (existeVersion == null) {

            nuevoCliente = true;

            Version version = new Version(1L, 1);
            version.persist(version);
            auxVersion = version;

        } else {
            auxVersion = existeVersion;
        }
        System.out.println("cliente nuevo " + this.nuevoCliente);

        return auxVersion;
    }

    public Kiosko KioscosListos() {
        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();
        Kiosko auxKiosco = null;
        Kiosko existeKiosco = null;
        try {
            existeKiosco = (Kiosko) manager.createQuery("SELECT ma FROM Kiosko ma WHERE ma.idKisco= :id").setParameter("id", 1L).getSingleResult();

        } catch (Exception e) {
        }

        if (existeKiosco == null) {
            Kiosko nuevoKisko = new Kiosko(1L, "6104", "localhost");
            nuevoKisko.persist(nuevoKisko);
            auxKiosco = nuevoKisko;

        } else {
            auxKiosco = existeKiosco;
        }
        return auxKiosco;
    }

    public Estudiante actualizarEstudiante(JSONObject dispositivokiosco, Usuario usuario) {
        //int Estudiante = dispositivokiosco.getJSONObject("estudiante").getInt("id");
        JSONObject estudiantekiosco = dispositivokiosco;

        Estudiante estudianteMac = null;

        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();

        Estudiante estudiante = manager.find(Estudiante.class, estudiantekiosco.getLong("id"));
        InfoGrado existeInfoGrado = null;
        try {
            existeInfoGrado = (InfoGrado) manager.createQuery("SELECT ma FROM InfoGrado ma WHERE ma.idInfoGrado= :id").setParameter("id", 1L).getSingleResult();

        } catch (Exception e) {
        }

        if (estudiante == null) {

            Estudiante estudianteNuevo = new Estudiante(
                    estudiantekiosco.getLong("id"),
                    estudiantekiosco.getString("nombre"),
                    estudiantekiosco.getString("apellidos"),
                    estudiantekiosco.getLong("id"),
                    usuario);
            estudianteNuevo.persist(estudianteNuevo);
            // estudianteCreado = estudiantekiosco.getLong("id");
            // estudiante = estudianteNuevo;
            System.out.println("creado");
            estudianteMac = estudianteNuevo;

        } else {
            manager.getTransaction().begin();
            estudiante.setNombres(estudiantekiosco.getString("nombre"));
            estudiante.setApellidos(estudiantekiosco.getString("apellidos"));
            estudiante.setEdad(estudiantekiosco.getLong("id"));

            manager.getTransaction().commit();
            manager.close();
            System.out.println("actualizado");
            // estudianteCreado = estudiantekiosco.getLong("id");
            estudianteMac = estudiante;

        }

        if (existeInfoGrado == null) {
            InfoGrado infoGrado = new InfoGrado(1L, "11", "TSG", "TSG", "Bogota", estudianteMac);
            infoGrado.persist(infoGrado);
        }

        return estudianteMac;

    }

    public ArrayList<Materias> actualizarMaterias(JSONArray jsonArrayMaterias) {

        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();
        ArrayList<Materias> auxMaterias = new ArrayList<>();

        for (int j = 0; j < jsonArrayMaterias.length(); j++) {
            JSONObject objMaterias = (JSONObject) jsonArrayMaterias.get(j);
            Materias existe = manager.find(Materias.class, objMaterias.getLong("id"));

            if (existe == null) {

                Materias materiasGuardar = new Materias(objMaterias.getLong("id"),
                        objMaterias.getString("titulo"),
                        objMaterias.getString("subtitulo"),
                        objMaterias.getString("descripcion"),
                        //objMaterias.getString("imagen"),
                        objMaterias.getString("subtitulo"));

                materiasGuardar.persist(materiasGuardar);
                auxMaterias.add(materiasGuardar);
                System.out.println("Materia Creaada" + materiasGuardar.getTitulo());

            } else {
                manager.getTransaction().begin();
                existe.setTitulo(objMaterias.getString("titulo"));
                existe.setSubtitulo(objMaterias.getString("subtitulo"));
                existe.setDescripcion(objMaterias.getString("descripcion"));
                // materias.get(i).setSubtitulo(objMaterias.getString("imagen"));
                existe.setCodigo(objMaterias.getString("subtitulo"));
                auxMaterias.add(existe);

                manager.getTransaction().commit();
                //manager.close();
                System.out.println("materia actualizada");

            }

        }

        //  manager.close();
        return auxMaterias;

    }

    public Set<Materias> AddEstudianteMaterias(JSONArray jsonArrayMaterias, Estudiante estudiante) {
        this.actualizarMaterias(jsonArrayMaterias);
        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();
        estudiante = manager.find(Estudiante.class, estudiante.getIdEstudiante());
        Set<Materias> materiasEstudiante = estudiante.getMateriases();

        for (int j = 0; j < jsonArrayMaterias.length(); j++) {
            JSONObject objMaterias = (JSONObject) jsonArrayMaterias.get(j);
            Materias materiasExiste = manager.find(Materias.class, objMaterias.getLong("id"));
            boolean existeMateriasEstudiante = materiasEstudiante.contains(materiasExiste);
          
            if (existeMateriasEstudiante == false) {
                manager.getTransaction().begin();
                estudiante.getMateriases().add(materiasExiste);
                manager.getTransaction().commit();

            }

        }

        //  manager.close();
        return estudiante.getMateriases();

    }

    public ArrayList<Clases> actualizarClases(JSONArray jsonArrayClases) throws SQLException {
        //int Estudiante = dispositivokiosco.getJSONObject("estudiante").getInt("id");
        //JSONObject estudiantekiosco = dispositivokiosco.getJSONObject("estudiante");

        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();
        ArrayList<Clases> arrayListClases = new ArrayList();

        for (int j = 0; j < jsonArrayClases.length(); j++) {
            JSONObject objClases = (JSONObject) jsonArrayClases.get(j);

            System.out.println(objClases.getLong("id"));

            Clases existe = manager.find(Clases.class, objClases.getLong("id"));
            Materias nueva = manager.find(Materias.class, objClases.getLong("materiaId"));

            if (existe == null) {

                Clases clasesGuardar = new Clases(objClases.getLong("id"),
                        objClases.getString("tema"),
                        objClases.getString("nombre"),
                        objClases.getString("fecha_inicio"),
                        nueva);
                clasesGuardar.persist(clasesGuardar);
                arrayListClases.add(clasesGuardar);
            } else {

                manager.getTransaction().begin();
                existe.setTema(objClases.getString("tema"));
                existe.setNombre(objClases.getString("nombre"));
                existe.setFechaInicio(objClases.getString("fecha_inicio"));
                existe.setMateria(nueva);
                manager.getTransaction().commit();
                // manager.close();
                System.out.println("clase actualizada");
                arrayListClases.add(existe);

            }

        }
        return arrayListClases;

    }

    public void actualizarMaterialEstudio(JSONArray jsonArrayMaterialEstudio) throws SQLException, MalformedURLException, IOException {
        //int Estudiante = dispositivokiosco.getJSONObject("estudiante").getInt("id");
        //JSONObject estudiantekiosco = dispositivokiosco.getJSONObject("estudiante");

        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();

        for (int j = 0; j < jsonArrayMaterialEstudio.length(); j++) {
            JSONObject objMaterialEstudio = (JSONObject) jsonArrayMaterialEstudio.get(j);
            System.out.println(objMaterialEstudio.getLong("id"));
            Clases nuevaClase = manager.find(Clases.class, objMaterialEstudio.getLong("claseId"));

            MaterialEstudio existe = null;

            try {
                existe = (MaterialEstudio) manager.createQuery("SELECT ma FROM MaterialEstudio ma WHERE ma.idMaterialEstudio= :id").setParameter("id", objMaterialEstudio.getLong("id")).getSingleResult();

            } catch (Exception e) {

            }

            if (existe == null) {

                int ext = 0;

                if (ext == 0) {
                    File Direccion = new File("Data/" + objMaterialEstudio.getInt("idD2L") + "BLOB");
                    int si = 1;
                    if (Direccion.exists()) {
                        if (Direccion.isDirectory()) {
                            System.out.println("Es una carpeta");
                        }
                    } else {
                        if (Direccion.mkdirs()) {
                            System.out.println("Directorio creado");
                        } else {
                            si = 0;
                            System.out.println("Error al crear directorio");
                        }
                    }

                    if (si != 0) {

                        String[] Archivo = objMaterialEstudio.getString("url").split("/");
                        Path dest = Paths.get("Data/" + objMaterialEstudio.getInt("idD2L") + "BLOB/" + Archivo[Archivo.length - 1]);
                        URL website = new URL(reemplazar(objMaterialEstudio.getString("url") + encode("", "UTF-8"), " ", "%20"));

                        try (InputStream in = website.openStream()) {
                            Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
                        }
                        MaterialEstudio materialEstudioGuardar = new MaterialEstudio(objMaterialEstudio.getLong("id"),
                                objMaterialEstudio.getString("tema"),
                                objMaterialEstudio.getString("descripcion"),
                                nuevaClase, objMaterialEstudio.getString("nombreArchivo"),
                                dest.toString());

                        materialEstudioGuardar.persist(materialEstudioGuardar);

                    }

                }

            }

        }
    }

    public boolean saveRuteBlob(String Ruta, int Subida, int Tarea, long Codigo) {

        return false;

    }

    public boolean updateBlo(JSONObject blob) throws MalformedURLException, IOException {

        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();
        boolean i = false;

        AchivosTot achivosTot = null;
        try {
            achivosTot = (AchivosTot) manager.createQuery("SELECT ma FROM AchivosTot ma WHERE ma.idAchivosTot= :id").setParameter("id", blob.getLong("codigo")).getSingleResult();

        } catch (Exception e) {
        }

        //res = state.executeQuery("SELECT * FROM Blob where Codigo = "+blob.getInt("codigo"));
        if (achivosTot == null) {

            int ext = 0;

            if (ext == 0) {
                File Direccion = new File("Data/" + blob.getInt("codigo") + "BLOB");
                int si = 1;
                if (Direccion.exists()) {
                    if (Direccion.isDirectory()) {
                        System.out.println("Es una carpeta");
                    }
                } else {
                    if (Direccion.mkdirs()) {
                        System.out.println("Directorio creado");
                    } else {
                        si = 0;
                        System.out.println("Error al crear directorio");
                    }
                }

                if (si != 0) {

                    String[] Archivo = blob.getString("file").split("/");
                    Path dest = Paths.get("Data/" + blob.getInt("codigo") + "BLOB/" + Archivo[Archivo.length - 1]);
                    URL website = new URL(blob.getString("file"));
                    try (InputStream in = website.openStream()) {
                        Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
                    }
                    AchivosTot achivosNuevo = new AchivosTot(blob.getLong("codigo"), String.valueOf(blob.getLong("codigo")), dest.toString());
                    achivosNuevo.persist(achivosNuevo);
                    System.err.println(dest.toFile());
                }

            }

        }

        return i;
    }

    public static String reemplazar(String cadena, String busqueda, String reemplazo) {
        return cadena.replaceAll(busqueda, reemplazo);
    }

    public Subida actualizarSubidas(JSONObject blob, Estudiante estudiante) {
        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();
        Subida subidasExistente = null;
        Subida subidaAux = null;
        try {
            subidasExistente = (Subida) manager.createQuery("SELECT ma FROM Subida ma WHERE ma.subidaKisoco= :id and ma.estudiante.idEstudiante=:idEstudiante").setParameter("id", blob.getJSONObject("file").getLong("descargaId")).setParameter("idEstudiante", estudiante.getIdEstudiante()).getSingleResult();

        } catch (Exception e) {
        }

        if (subidasExistente == null) {

            Subida subidaTarea = new Subida(blob.getJSONObject("file").getLong("descargaId"), blob.getString("fechaDescarga"), "2020", estudiante);
            subidaTarea.persist(subidaTarea);
            subidaAux = subidaTarea;
        } else {
            subidaAux = subidasExistente;
        }

        return subidaAux;
    }

    public void updateBlobTareas(JSONObject blob, String ip, Estudiante estudiante) throws MalformedURLException, IOException {
        Subida subidaTarea = actualizarSubidas(blob, estudiante);
        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();

        AchivosTot achivosTot = null;
        try {
            achivosTot = (AchivosTot) manager.createQuery("SELECT ma FROM AchivosTot ma WHERE ma.archivoKiosco= :id AND ma.subida.subidaKisoco=: idSubidaKiosco").setParameter("id", blob.getLong("id")).setParameter("idSubidaKiosco", blob.getJSONObject("file").getLong("descargaId")).getSingleResult();

        } catch (Exception e) {
        }

        if (achivosTot == null) {

            int ext = 0;

            if (ext == 0) {
                File Direccion = new File("Data/" + estudiante.getNombres() + "/" + blob.getInt("tareaId") + "BLOB");
                int si = 1;
                if (Direccion.exists()) {
                    if (Direccion.isDirectory()) {
                        System.out.println("Es una carpeta");
                    }
                } else {
                    if (Direccion.mkdirs()) {
                        System.out.println("Directorio creado");
                    } else {
                        si = 0;
                        System.out.println("Error al crear directorio");
                    }
                }

                if (si != 0) {

                    if (blob.getJSONObject("file").getString("url").equals("http://" + ip + "/media")) {

                    } else {
                        String[] Archivo = blob.getJSONObject("file").getString("url").split("/");
                        Path dest = Paths.get("Data/" + estudiante.getNombres() + "/" + blob.getInt("tareaId") + "BLOB/" + Archivo[Archivo.length - 1]);
                        URL website = new URL(reemplazar(blob.getJSONObject("file").getString("url") + encode("", "UTF-8"), " ", "%20"));
                        try (InputStream in = website.openStream()) {
                            Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
                        }

                        AchivosTot achivosNuevo = new AchivosTot(blob.getJSONObject("file").getLong("id"), String.valueOf(blob.getJSONObject("file").getLong("id")), dest.toString(), subidaTarea);
                        achivosNuevo.persist(achivosNuevo);

                    }
                }
            }

        }

    }

    public Tareas actualizarTareas(JSONObject jsonTareas, String ip, Estudiante estudiante, Long registroTarea) throws SQLException {

        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();

        System.out.println(jsonTareas.getLong("tareaId"));

        //BlobsKiosco blobagregar = manager.find(BlobsKiosco.class, 527789L);
        Tareas existe = null;
        Materias materiaTarea = null;
        Tareas auxTareas = null;

        if (estudiante != null) {

            try {
                existe = (Tareas) manager.createQuery("SELECT ma FROM Tareas ma WHERE ma.tareaKiosco= :id and ma.estudiante.idEstudiante= : idEstudiante").setParameter("id", jsonTareas.getLong("tareaId")).setParameter("idEstudiante", estudiante.getIdEstudiante()).getSingleResult();

            } catch (Exception e) {

            }

            try {
                materiaTarea = (Materias) manager.createQuery("SELECT ma FROM Materias ma WHERE ma.idMateria= :id ").setParameter("id", jsonTareas.getLong("materiaId")).getSingleResult();
                System.out.println("materias tareas");
            } catch (Exception e) {

            }

            if (existe == null) {
                Subida subidasExistente = null;
                try {
                    subidasExistente = (Subida) manager.createQuery("SELECT ma FROM Subida ma WHERE ma.subidaKisoco= :id  and ma.estudiante.idEstudiante= : idEstudiante").setParameter("id", jsonTareas.getJSONObject("subida").getLong("id")).setParameter("idEstudiante", estudiante.getIdEstudiante()).getSingleResult();

                } catch (Exception e) {
                }

                if (subidasExistente != null) {
                    Tareas tareasguardar = new Tareas(jsonTareas.getLong("tareaId"), registroTarea, jsonTareas.getString("nombre"), jsonTareas.getString("codigo"), materiaTarea, subidasExistente, estudiante);
                    tareasguardar.persist(tareasguardar);
                    auxTareas = tareasguardar;

                    System.err.println("Tareas nueva" + "subida existente ");
                } else {

                    Subida subidaTarea = new Subida(jsonTareas.getJSONObject("file").getLong("descargaId"), jsonTareas.getString("fechaDescarga"), jsonTareas.getString("fechaDescarga"), estudiante);
                    subidaTarea.persist(subidaTarea);

                    Tareas tareasguardar = new Tareas(jsonTareas.getLong("tareaId"), registroTarea, jsonTareas.getString("nombreActividad"), String.valueOf(jsonTareas.getLong("idArchivoD2L")), materiaTarea, subidaTarea, estudiante);
                    tareasguardar.persist(tareasguardar);
                    auxTareas = tareasguardar;
                    System.err.println("Tareas nueva" + "subida nueva ");

                }

            } else {

                // manager.getTransaction().begin();
                existe.setCodigo(String.valueOf(jsonTareas.getLong("idArchivoD2L")));
                existe.setNombreTarea(jsonTareas.getString("nombreActividad"));
                existe.setRegistroTarea(registroTarea);
                existe.setMateria(materiaTarea);
                auxTareas = existe;
                manager.close();
                System.out.println("actualizado");

            }
        }

        return auxTareas;

    }

    public void postArchivos(String ip, String token, Estudiante estudiante) throws Exception {
        String endPointPostArchivo = "http://" + ip + "/api/entregas/entregarMiTarea";

        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();
        // List<Entregas> entregasEnviar = manager.createQuery("FROM Entregas").getResultList();

        List<AchivosTot> entregas = manager.createQuery("SELECT ma FROM AchivosTot ma WHERE ma.entrega.upp= :id").setParameter("id", 0L).getResultList();
         System.out.println(entregas);

        for (AchivosTot entrega : entregas) {
              System.out.println(entrega.getEntrega().getEstudiante() + "Estudiante Entrega");
                System.out.println(estudiante + "Estudiante activo sin el if ");
          //  if (entrega.getEntrega().getEstudiante().equals(estudiante)) {
                System.out.println(entrega.getEntrega().getEstudiante() + "Estudiante Entrega");
                System.out.println(estudiante + "Estudiante activo");

                JSONObject obArchivos = new JSONObject();
                obArchivos.put("File", entrega.getRuta());
                obArchivos.put("TareaRegistroId", entrega.getEntrega().getRtEntrega());
                obArchivos.put("MAC", Inicio.getMacAddress());

                inicio.sendhttppostwhitfile(endPointPostArchivo, obArchivos, token);
                System.err.println("Se envio el archivo");
              //  manager.getTransaction().begin();
              //  entrega.getEntrega().setUpp(1L);
              //  manager.getTransaction().commit();
           // }

        }
    }

    public void actualizarVersion(double numero) {

        enf = Persistence.createEntityManagerFactory("tsg");

        manager = enf.createEntityManager();
        Version version = (Version) manager.createQuery("SELECT ma FROM Version ma WHERE ma.idVersion= :id").setParameter("id", 1L).getSingleResult();

        manager.getTransaction().begin();
        version.setNumero(numero);
        manager.getTransaction().commit();
    }

    public void TareaRealizada(String ip) {

        String url = "";
    }

    public boolean isNuevoCliente() {
        return nuevoCliente;
    }

    public void setNuevoCliente(boolean nuevoCliente) {
        this.nuevoCliente = nuevoCliente;
    }

    public List<Estudiante> ListarEstudiantes() {
        enf = Persistence.createEntityManagerFactory("tsg");
        manager = enf.createEntityManager();

        List<Estudiante> estudiantesGuardados = manager.createQuery("FROM  Estudiante").getResultList();

        return estudiantesGuardados;

    }

}
