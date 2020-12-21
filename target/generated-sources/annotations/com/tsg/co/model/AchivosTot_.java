package com.tsg.co.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AchivosTot.class)
public abstract class AchivosTot_ {

	public static volatile SingularAttribute<AchivosTot, String> codigo;
	public static volatile SingularAttribute<AchivosTot, String> ruta;
	public static volatile SetAttribute<AchivosTot, MaterialEstudio> materialEstudios;
	public static volatile SingularAttribute<AchivosTot, Long> idAchivosTot;
	public static volatile SingularAttribute<AchivosTot, Subida> subida;
	public static volatile SingularAttribute<AchivosTot, Entregas> entrega;

}

