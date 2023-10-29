package com.dbms.sms.entity;

import com.dbms.sms.entity.Class;

import jakarta.persistence.*;

@Entity
@Table(name="teachers")
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="emp_id")
	private Long employeeId;
	
	@Column(name="firstname",nullable=false)
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String address;
	
	@Column(name="contact")
	private String contact;
	
	private String joiningDate;
	
	private String status;
	
	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId2) {
		this.employeeId = employeeId2;
	}

	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Subject getSubId() {
		return subId;
	}

	public void setSubId(Subject subId) {
		this.subId = subId;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@ManyToOne
	@JoinColumn(name = "subject_id", referencedColumnName = "sub_id")
	private Subject subId;
	
	@Column(name="salary")
	private int salary;

	
}
