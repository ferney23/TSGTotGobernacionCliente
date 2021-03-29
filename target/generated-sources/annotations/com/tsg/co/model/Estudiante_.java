package com.tsg.co.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Estudiante.class)
public abstract class Estudiante_ {

	public static volatile SingularAttribute<Estudiante, String> apellidos;
	public static volatile SetAttribute<Estudiante, MensajeKiosco> mensajeKioscos;
	public static volatile SetAttribute<Estudiante, Materias> materiases;
	public static volatile SetAttribute<Estudiante, Subida> subidas;
	public static volatile SetAttribute<Estudiante, Entregas> entregas;
	public static volatile SingularAttribute<Estudiante, Long> idEstudiante;
	public static volatile SingularAttribute<Estudiante, Usuario> usuario;
	public static volatile SetAttribute<Estudiante, Tareas> tareas;
	public static volatile SingularAttribute<Estudiante, Long> edad;
	public static volatile SetAttribute<Estudiante, InfoGrado> infoGrados;
	public static volatile SingularAttribute<Estudiante, String> nombres;

}

