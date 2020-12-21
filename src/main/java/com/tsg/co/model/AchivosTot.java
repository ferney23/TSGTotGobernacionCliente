package com.tsg.co.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferney
 */
@Entity
@Table(name = "archivosKiscos")
public class AchivosTot implements Serializable {

    @Id
    private Long idAchivosTot;

    @Column(name = "codigo", nullable = true, length = 250)
    private String codigo;

    @Column(name = "ruta", nullable = true, length = 250)
    private String ruta;

    

    @ManyToOne(optional = true)
    private Subida subida;

    @ManyToOne(optional = true)
    private Entregas entrega;
    
    @OneToMany(mappedBy = "achivosTot")
    private Set<MaterialEstudio> materialEstudios =  new HashSet<>();

    public AchivosTot() {
    }

    public AchivosTot(Long idAchivosTot, String codigo, String ruta,Subida subida) {
        this.idAchivosTot = idAchivosTot;
        this.codigo = codigo;
        this.ruta = ruta;
        this.subida= subida;

    }
    
    
    public AchivosTot(Long idAchivosTot, String codigo, String ruta,Subida subida,Entregas entregas) {
        this.idAchivosTot = idAchivosTot;
        this.codigo = codigo;
        this.ruta = ruta;
        this.subida= subida;
        this.entrega= entregas;

    }

    
    public AchivosTot(Long idAchivosTot, String codigo, String ruta) {
        this.idAchivosTot = idAchivosTot;
        this.codigo = codigo;
        this.ruta = ruta;
        

    }
    public Long getIdAchivosTot() {
        return idAchivosTot;
    }

    public void setIdAchivosTot(Long idAchivosTot) {
        this.idAchivosTot = idAchivosTot;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Subida getSubida() {
        return subida;
    }

    public void setSubida(Subida subida) {
        this.subida = subida;
    }

    public Entregas getEntrega() {
        return entrega;
    }

    public void setEntrega(Entregas entrega) {
        this.entrega = entrega;
    }

    public Set<MaterialEstudio> getMaterialEstudios() {
        return materialEstudios;
    }

    public void setMaterialEstudios(Set<MaterialEstudio> materialEstudios) {
        this.materialEstudios = materialEstudios;
    }

    @Override
    public String toString() {
        return idAchivosTot + ruta;
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tsg");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
