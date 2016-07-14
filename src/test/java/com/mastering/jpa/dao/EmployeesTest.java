package com.mastering.jpa.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
		
		Query queryRemovingEmployees = manager.createQuery("delete from Employee");
		queryRemovingEmployees.executeUpdate();
		
		Query queryRemovingDepartments = manager.createQuery("delete from Department");
		queryRemovingDepartments.executeUpdate();
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

	/**
	 * Description: We must create a search criteria with the following required steps:
	 * 
	 * - Must return just employees with 30 OR 28 yeas 
	 *   in this case, Alexandre Gama, Gustavo Borges and Otavio Santana will be returned
	 * - Must return the before query AND users working on third and forth Department
	 *   in this case, Carlos Eduardo AND Fernado Lima wll be returned
	 *  
	 * Actually, after the query, all users need to be returned  
	 */
	@Test
	public void shouldReturnAllEmployeesThatMatchWithConditionsUsingCriteriaApi() throws Exception {
		Department firstDepartmentSaved = createNewDepartment();
		Department secondDepartmentSaved = createNewDepartment();
		Department thirdDepartmentSaved = createNewDepartment();
		Department forthDepartmentSaved = createNewDepartment();
		
		Employees employees = new EmployeeDao(manager);
		Employee alexandreGama = employees.save(new Employee("Alexandre Gama", 30, firstDepartmentSaved));
		employees.save(alexandreGama);
		
		Employee fernandoLima = employees.save(new Employee("Fernado Lima", 29, forthDepartmentSaved));
		employees.save(fernandoLima);
		
		Employee gustavoBorges = employees.save(new Employee("Gustavo Borges", 30, firstDepartmentSaved));
		employees.save(gustavoBorges);
		
		Employee otavioSantana = employees.save(new Employee("Otavio Santana", 28, secondDepartmentSaved));
		employees.save(otavioSantana);
		
		Employee carlosEduardo = employees.save(new Employee("Carlos Eduardo", 27, thirdDepartmentSaved));
		employees.save(carlosEduardo);
		
		
	}
	
	private Department createNewDepartment() {
		Departments departments = new DepartmentDao(manager);
		
		Department department = new Department("Engineering-" + new Random().nextInt(10), 10);
		Department departmentSaved = departments.save(department);
		return departmentSaved;
	}
	
}
