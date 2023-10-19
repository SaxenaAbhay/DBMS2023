package com.dbms.sms.entity;


import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="scholar_id")
	private Long scholarId;
	
	@Column(name="first_name",nullable=false)
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="dob")
	private String dob;
	
	@Column(name="contact")
	private String contact;
	
	@Column(name="parent_name")
	private String parentName;
	
	@ManyToOne
	@JoinColumn(name = "class", referencedColumnName = "class_id")
	private Class classroom;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Score> scoreList;
	
}