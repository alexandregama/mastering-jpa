package com.mastering.jpa.dao;

import javax.persistence.EntityManager;

import com.mastering.jpa.model.Employee;

class EmployeeDao implements Employees {

	private EntityManager manager;

	public EmployeeDao(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public Employee save(Employee employee) {
		manager.persist(employee);
		
		return manager.find(Employee.class, employee.getId());
	}

}
