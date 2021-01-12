/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.co.Persistencia;

import com.tsg.co.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ferney
 */
public class ManejoDatos {

    private  EntityManager manager;
    private  EntityManagerFactory enf;

    public ManejoDatos() {
    }

    public List<Usuario> listarUsuarios() {

    //    enf = Persistence.createEntityManagerFactory("tsg");
    //    manager = enf.createEntityManager();
        List<Usuario> usuariosExistentes = manager.createQuery("SELECT ma FROM  Usuario ma").getResultList();

        return usuariosExistentes;
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public EntityManagerFactory getEnf() {
        return enf;
    }

    public void setEnf(EntityManagerFactory enf) {
        this.enf = enf;
    }
    
    
}
