package com.mastering.jpa.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Random;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mastering.jpa.model.Department;
import com.mastering.jpa.model.Employee;
import com.mastering.jpa.model.JPAUtil;

public class EmployeesTest {

	private EntityManager manager;

	@Before
	public void before() {
		manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
	}
	
	@After
	public void after() {
		manager.getTransaction().commit();
		manager.close();
	}
	
	@Test
	public void shouldSaveANewEmployee() throws Exception {
		Department departmentSaved = createNewDepartment();
		
		Employees employees = new EmployeeDao(manager);
		Employee employeeSaved = employees.save(new Employee("Alexandre Gama", 30, departmentSaved));

		assertThat(employeeSaved.getName(), is(equalTo("Alexandre Gama")));
	}

	private Department createNewDepartment() {
		Departments departments = new DepartmentDao(manager);
		
		Department department = new Department("Engineering-" + new Random().nextInt(10), 10);
		Department departmentSaved = departments.save(department);
		return departmentSaved;
	}
	
}
