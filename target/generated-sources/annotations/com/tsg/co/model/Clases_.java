package com.tsg.co.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Clases.class)
public abstract class Clases_ {

	public static volatile SingularAttribute<Clases, String> tema;
	public static volatile SingularAttribute<Clases, String> fechaInicio;
	public static volatile ListAttribute<Clases, MaterialEstudio> materialEstudios;
	public static volatile SingularAttribute<Clases, Long> idClases;
	public static volatile SingularAttribute<Clases, Materias> materia;
	public static volatile SingularAttribute<Clases, Profesor> profesor;
	public static volatile SingularAttribute<Clases, String> nombre;
	public static volatile SingularAttribute<Clases, String> codigoClase;

}

