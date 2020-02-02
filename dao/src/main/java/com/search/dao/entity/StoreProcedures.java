package com.search.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "store_proceduress")
@NamedStoredProcedureQueries({
   @NamedStoredProcedureQuery(name = "build_user_meta", 
                              procedureName = "build_user_meta",
                              parameters = {
                                 @StoredProcedureParameter(
                                		 mode = ParameterMode.IN, 
                                		 name = "userId", 
                                		 type = Long.class),
                                 @StoredProcedureParameter(
                                		 mode = ParameterMode.IN, 
                                		 name = "token", 
                                		 type = String.class)
                              }),
   @NamedStoredProcedureQuery(name = "proc_get_user_search", 
                              procedureName = "proc_get_user_search",
                              parameters = {
                                 @StoredProcedureParameter(
                                		 mode = ParameterMode.IN, 
                                		 name = "userId", 
                                		 type = Long.class),
                                 @StoredProcedureParameter(
                                		 mode = ParameterMode.IN, 
                                		 name = "token", 
                                		 type = String.class),
                                 @StoredProcedureParameter(
                                		 mode = ParameterMode.INOUT, 
                                		 name = "name", 
                                		 type = String.class),
                                 @StoredProcedureParameter(
                                		 mode = ParameterMode.INOUT, 
                                		 name = "profile", 
                                		 type = String.class)
                              })
})
public class StoreProcedures implements Serializable {

	private static final long serialVersionUID = -4967673982805437209L;
	

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.AUTO )
	private Long id;
	
	private String name;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StoreProcedures [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
}