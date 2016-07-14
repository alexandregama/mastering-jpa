package com.mastering.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mastering-jpa");
		
		EntityManager manager = factory.createEntityManager();
		manager.close();
	}
	
}
