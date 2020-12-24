package com.tsg.co.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tareas.class)
public abstract class Tareas_ {

	public static volatile SingularAttribute<Tareas, Estudiante> estudiante;
	public static volatile SingularAttribute<Tareas, String> codigo;
	public static volatile SingularAttribute<Tareas, Long> tareaKiosco;
	public static volatile SetAttribute<Tareas, Entregas> entregas;
	public static volatile SingularAttribute<Tareas, String> nombreTarea;
	public static volatile SingularAttribute<Tareas, Long> registroTarea;
	public static volatile SingularAttribute<Tareas, Materias> materia;
	public static volatile SingularAttribute<Tareas, Subida> subida;
	public static volatile SingularAttribute<Tareas, Long> id;

}

