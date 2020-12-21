package com.tsg.co.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Materias.class)
public abstract class Materias_ {

	public static volatile SingularAttribute<Materias, String> descripcion;
	public static volatile SingularAttribute<Materias, String> codigo;
	public static volatile SingularAttribute<Materias, String> subtitulo;
	public static volatile SingularAttribute<Materias, Long> idMateria;
	public static volatile SetAttribute<Materias, Clases> clases;
	public static volatile SingularAttribute<Materias, String> titulo;
	public static volatile SingularAttribute<Materias, String> imagen;
	public static volatile SingularAttribute<Materias, Profesor> profesor;
	public static volatile SetAttribute<Materias, Estudiante> estudiantes;
	public static volatile SetAttribute<Materias, Tareas> tareas;

}

