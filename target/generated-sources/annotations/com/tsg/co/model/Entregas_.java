package com.tsg.co.model;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Entregas.class)
public abstract class Entregas_ {

	public static volatile SingularAttribute<Entregas, Estudiante> estudiante;
	public static volatile SingularAttribute<Entregas, String> codigoTarea;
	public static volatile SingularAttribute<Entregas, Tareas> tarea;
	public static volatile SingularAttribute<Entregas, String> codigoEntrega;
	public static volatile SingularAttribute<Entregas, Calendar> creado;
	public static volatile SetAttribute<Entregas, AchivosTot> blob;
	public static volatile SingularAttribute<Entregas, Long> upp;
	public static volatile SingularAttribute<Entregas, Long> rtEntrega;
	public static volatile SingularAttribute<Entregas, Subida> subida;
	public static volatile SingularAttribute<Entregas, Long> id;

}

