/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.test;

import com.tsg.co.Persistencia.ObtenerDatos;
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
import com.tsg.co.model.Kiosko;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

/**
 *
 * @author Ferney
 */
public class Test {

    private static EntityManager manageres;
    private static EntityManagerFactory enf;

    public static void main(String[] args) {

        enf = Persistence.createEntityManagerFactory("tsg");
        manageres = enf.createEntityManager();

//       AchivosTot achivosTot = (AchivosTot) manageres.createQuery("SELECT ma FROM AchivosTot ma WHERE ma.archivoKiosco= :id AND ma.subida.subidaKisoco=: idSubidaKiosco").setParameter("id", 4L).setParameter("idSubidaKiosco", 4L).getSingleResult();
        //  System.out.println(achivosTot.getRuta());
        // Estudiante estudiante = (Estudiante) manageres.createQuery("SELECT ma FROM Estudiante ma WHERE ma.idEstudiante= :id").setParameter("id", 209L).getSingleResult();
        //  Usuario usuariosExistentes = (Usuario) manageres.createQuery("SELECT ma FROM  Usuario ma WHERE ma.username =:usuario and ma.contraseña =:contraseña ").setParameter("usuario", "estudiante.uno").setParameter("contraseña", "tsg123").getSingleResult();
        // System.out.println(usuariosExistentes.getEstudiante().getNombres());
        Estudiante estudiante = manageres.find(Estudiante.class, 209L);
        Set<Materias> materiasEstudiante = estudiante.getMateriases();
        Materias materiasExiste = manageres.find(Materias.class, 6771L);
        //System.out.println(materiasEstudiante);
        //System.out.println(materiasEstudiante.contains(materiasExiste) + materiasExiste.getTitulo());

        Materias materiaTarea = (Materias) manageres.createQuery("SELECT ma FROM Materias ma WHERE ma.idMateria= :id ").setParameter("id", 6760L).getSingleResult();
        System.out.println(materiaTarea.getTitulo());

    }

    /**
     * Estudiante estudiante = manageres.find(Estudiante.class, 209L);
     * Set<Materias> materiases = estudiante.getMateriases();
     *
     * for (Materias materiase : materiases) { //
     * System.out.println(materiase.getTitulo());
     *
     * }
     *
     * // Materias materias = manageres.find(Materias.class, 6777L);
     * List<Tareas> tareas = manageres.createQuery("SELECT ma FROM Tareas ma
     * WHERE ma.materia.idMateria= :id and ma.estudiante.idEstudiante=
     * :idEstudiante").setParameter("id", 6763L).setParameter("idEstudiante",
     * 209L).getResultList(); for (Tareas tarea : tareas) {
     * System.out.println(tarea.getNombreTarea()); }
     *
     * // ObtenerDatos obtenerDatos = new ObtenerDatos(); //
     * obtenerDatos.ListarEstudiaantes(); //
     * System.out.println(obtenerDatos.ListarEstudiantes()); // Estudiante
     * estudiante1 = new Estudiante(3L, "Andres", "Nino", 0L); // Estudiante
     * estudiante1 = manageres.find(Estudiante.class, 212l); //
     * System.out.println(estudiante1.getNombres()); //
     * manageres.getTransaction().begin(); // estudiante1.addMaterias(
     * manageres.find(Materias.class, 201L)); // estudiante1.addMaterias(new
     * Materias(20000L, "Gerencia", "Gerencia", "Aprender de gerencia", "uno"));
     * // manageres.getTransaction().commit();
     * //estudiante1.persist(estudiante1); /**
     *
     *
     * List<Entregas> entregasEnviar = manageres.createQuery("FROM
     * Entregas").getResultList();
     *
     *
     * for (int i = 0; i < entregasEnviar.size(); i++) {
     * System.out.println(entregasEnviar.get(i).getId());
     *
     * }
     *
     */
    /**
     * Para traer material de estudio
     */
    //List<MaterialEstudio> materialEstudio =  manageres.createQuery("SELECT ma FROM MaterialEstudio ma WHERE ma.clase.materia.idMateria= :id").setParameter("id", 2L).getResultList();
    //System.out.println(materialEstudio);
    //  List<AchivosTot> achivosTots =  manageres.createQuery("SELECT ma FROM AchivosTot ma WHERE ma.subida.tareas.id= :id").setParameter("id", 4L).getResultList();
    //System.out.println(achivosTots);
    // List<Subida> achivosTots = manageres.createQuery("FROM Subida").getResultList();
    /**
     * List<AchivosTot> achivosTot = manageres.createQuery("SELECT ma FROM
     * AchivosTot ma WHERE ma.subida.idSubida= :id").setParameter("id",
     * 921).getResultList(); for (int i = 0; i < achivosTot.size(); i++) {
     * AchivosTot get = achivosTot.get(i);
     *
     *
     *
     * }
     *
     * //System.out.println(achivosTot + "Ferney si se puede"); * //AchivosTot
     * achivosTot = (AchivosTot) manageres.createQuery("SELECT ma FROM
     * AchivosTot ma WHERE ma.entrega.id= :id").setParameter("id",
     * 3L).getSingleResult();
     *
     *
     * //AchivosTot tareasAchivos = (AchivosTot) manageres.createQuery("SELECT
     * ma FROM AchivosTot ma WHERE ma.idAchivosTot= :id").setParameter("id",
     * 527789L).getSingleResult();
     *
     * //System.out.println(tareasAchivos);
     *
     *
     * // Tareas tareasAchiv = (Tareas) manageres.createQuery("SELECT ma FROM
     * Tareas ma WHERE ma.id= :id").setParameter("id", 794L).getSingleResult();
     *
     * // Tareas tareas = manageres.find(Tareas.class, 794L);
     * //System.out.println(tareas.getSubida());
     *
     * //InfoGrado existeInfoGrado = (InfoGrado) manageres.createQuery("SELECT
     * ma FROM InfoGrado ma WHERE ma.idInfoGrado= :id").setParameter("id",
     * 1L).getSingleResult(); //System.out.println("Informacion del grado " +
     * existeInfoGrado); //Kiosko existeKiosco = (Kiosko)
     * manageres.createQuery("SELECT ma FROM Kiosko ma WHERE ma.idKisco=
     * :id").setParameter("id", 1L).getSingleResult(); //
     * System.out.println("Informacion del kiosco " + existeKiosco);
     *
     *
     * }
     *
     * //MaterialEstudio existe = manageres.find(MaterialEstudio.class, 1L);
     * //System.out.println(existe+ "ferney");
     * //manageres.getTransaction().begin(); //AchivosTot existe =
     * manageres.find(AchivosTot.class, 527789l); //List<MaterialEstudio>
     *
     * //System.out.println(materialEstudios); // Materias materias =
     * (Materias) manager.createQuery(" FROM Materias WHERE idMateria=
     * :id").setParameter("id", 23); //List<Clases> materialEstudios =
     * manager.createQuery("FROM Clases").getResultList(); //
     * System.out.println(materialEstudios); //List<Materias> materias =
     * manager.createQuery("FROM Materias").getResultList();
     * //System.out.println(materias); /**
     *
     *
     * for (int i = 0; i < materias.size(); i++) {
     * System.out.println(materias.get(i).getCodigo()); }
     */
    /**
     * Materias materias1 = manager.find(Materias.class, 1L); List<Tareas>
     * tareas = materias1.getTareas(); for (Tareas tarea : tareas) {
     * System.out.println(tarea.getNombreTarea()); }
     *
     *
     */
}
