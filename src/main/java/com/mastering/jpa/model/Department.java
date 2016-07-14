package com.mastering.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "department")
@Entity
public class Department implements Serializable {

	private static final long serialVersionUID = 4648761912328343836L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", length = 30)
	private String name;

	@Column(name = "floor_number")
	private int floorNumber;
	
	public Department(String name, int floorNumber) {
		this.name = name;
		this.floorNumber = floorNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public Long getId() {
		return id;
	}

}
