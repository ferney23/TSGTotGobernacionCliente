package com.tsg.co.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Profesor.class)
public abstract class Profesor_ {

	public static volatile SingularAttribute<Profesor, String> apellidos;
	public static volatile SingularAttribute<Profesor, Long> codigo;
	public static volatile SingularAttribute<Profesor, Long> idProfesor;
	public static volatile SetAttribute<Profesor, Clases> clases;
	public static volatile SingularAttribute<Profesor, String> nombre;
	public static volatile SetAttribute<Profesor, Materias> materias;

}

