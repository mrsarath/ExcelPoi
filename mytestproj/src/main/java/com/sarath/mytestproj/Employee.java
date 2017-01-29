package com.sarath.mytestproj;

public class Employee {
	
	String name;
	String department;
	
	public Employee(String name, String dept) {
		this.name = name;
		this.department = dept;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + "]";
	}
		
	

}
