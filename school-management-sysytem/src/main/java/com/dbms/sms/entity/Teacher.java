package com.dbms.sms.entity;

import com.dbms.sms.entity.Class;

import jakarta.persistence.*;

@Entity
@Table(name="teachers")
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="emp_id")
	private int employeeId;
	
	@Column(name="first_name",nullable=false)
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String address;
	
	@Column(name="contact")
	private String contact;
	
	@ManyToOne
	@JoinColumn(name = "subject_id", referencedColumnName = "sub_id")
	Subject subId;
	
	@Column(name="salary")
	private int salary;
	
	
	
}
