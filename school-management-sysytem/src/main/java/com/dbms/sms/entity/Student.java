package com.dbms.sms.entity;


import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="roll")
	private Long roll;
	
	@Column(name="first_name",nullable=false)
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="dob")
	private String dob;
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Student() {
		
	}
	public Long getRoll() {
		return roll;
	}
	public void setId(Long roll) {
		this.roll = roll;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Student(Long roll,String firstName, String lastName, String email,String dob) {
		super();
		this.roll=roll;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob=dob;
	}
	
}
