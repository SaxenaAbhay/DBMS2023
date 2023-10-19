package com.dbms.sms.entity;


import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="Student")
public class Student {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="scholar_id")
	private Long scholarId;
	
	@Column(name="firstname", nullable = false)
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="dob")
	private String dob;
	
	@Column(name="contact")
	private String contact;
	
	@Column(name="parent_name")
	private String parentName;
	
	public Long getScholarId() {
		return scholarId;
	}

	public void setScholarId(Long scholarId) {
		this.scholarId = scholarId;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Class getClassroom() {
		return classroom;
	}

	public void setClassroom(Class classroom) {
		this.classroom = classroom;
	}

	public List<Score> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<Score> scoreList) {
		this.scoreList = scoreList;
	}

	@ManyToOne
	@JoinColumn(name = "class", referencedColumnName = "class_id")
	private Class classroom;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Score> scoreList;
	
}
