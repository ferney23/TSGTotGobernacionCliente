package com.tsg.co.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Subida.class)
public abstract class Subida_ {

	public static volatile SingularAttribute<Subida, Long> subidaKisoco;
	public static volatile SingularAttribute<Subida, Estudiante> estudiante;
	public static volatile SingularAttribute<Subida, String> fecha;
	public static volatile SetAttribute<Subida, AchivosTot> achivosTot;
	public static volatile SetAttribute<Subida, Entregas> entregas;
	public static volatile SingularAttribute<Subida, Long> idSubida;
	public static volatile SingularAttribute<Subida, String> fechaDescarga;
	public static volatile SetAttribute<Subida, Tareas> tareas;

}

