package com.dbms.sms.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="class")
public class Class {
//	@EmbeddedId classId class_id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="class_id")
	private Long classId;
	
	@Column(name="standard")
	private int standard;
	
	@Column(name="section")
	private char section;
	
	
	

	public int getStandard() {
		return standard;
	}


	public void setStandard(int standard) {
		this.standard = standard;
	}


	public char getSection() {
		return section;
	}


	public void setSection(char section) {
		this.section = section;
	}


	public List<Student> getStudentList() {
		return studentList;
	}


	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}


	@OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Student> studentList;




	public Long getClassId() {
		return classId;
	}


	public void setClassId(Long classId) {
		this.classId = classId;
	}
}
//class classId{
//	
//	@Column(name="standard")
//	private int standard;
//	
//	@Column(name="section")
//	private char section;
//}