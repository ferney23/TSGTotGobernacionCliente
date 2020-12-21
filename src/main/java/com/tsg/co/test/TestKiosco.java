/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.test;

import com.tsg.co.Sincronizacion.Inicio;
import com.tsg.co.model.AchivosTot;
import com.tsg.co.model.Entregas;
import com.tsg.co.model.Materias;
import com.tsg.co.model.Tareas;
import com.tsg.model.Kiosko;
import com.tsg.co.model.Version;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.json.JSONObject;

/**
 *
 * @author Ferney
 */
public class TestKiosco {

    private static EntityManager em;
    private static EntityManagerFactory enf;

    public static void main(String[] args) throws IOException {
        enf = Persistence.createEntityManagerFactory("tsg");
        em = enf.createEntityManager();
        Inicio inicio = new Inicio();

        List<AchivosTot> entregas = em.createQuery("SELECT ma FROM AchivosTot ma WHERE ma.entrega.upp= :id").setParameter("id", 0L).getResultList();
        // System.out.println(entregas);

        for (AchivosTot entrega : entregas) {

            JSONObject obArchivos = new JSONObject();
            obArchivos.put("File", entrega.getRuta());
            obArchivos.put("TareaRegistroId", entrega.getEntrega().getRtEntrega());
            obArchivos.put("EstudianteId", entrega.getEntrega().getEstudiante().getIdEstudiante());

            System.out.println(obArchivos);

            inicio.sendhttppostwhitfile("http://localhost:6104/api/Entregas", obArchivos);

            em.getTransaction().begin();
            entrega.getEntrega().setUpp(1L);
            em.getTransaction().commit();

        }

        /**
         * em.getTransaction().begin();
         *
         *
         * em.getTransaction().commit(); em.close();
         *
         */
    }

}
