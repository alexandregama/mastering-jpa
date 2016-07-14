package com.mastering.jpa.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
 	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("mastering-jpa");

	public EntityManager getEntityManager() {
		EntityManager manager = factory.createEntityManager();
		
		return manager;
	}
	
}
