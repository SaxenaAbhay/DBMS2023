package com.dbms.sms.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="class")
public class Class {
//	@EmbeddedId classId class_id;
	
	@Id
	@Column(name="class_id")
	private int classId;
	
	@Column(name="standard")
	private int standard;
	
	@Column(name="section")
	private char section;
	
	
	@OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Student> studentList;
}
//class classId{
//	
//	@Column(name="standard")
//	private int standard;
//	
//	@Column(name="section")
//	private char section;
//}