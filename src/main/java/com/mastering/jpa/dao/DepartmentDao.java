package com.mastering.jpa.dao;

import javax.persistence.EntityManager;

import com.mastering.jpa.model.Department;

class DepartmentDao implements Departments {

	private EntityManager manager;

	public DepartmentDao(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public Department save(Department department) {
		manager.persist(department);
		
		return manager.find(Department.class, department.getId());
	}

}
