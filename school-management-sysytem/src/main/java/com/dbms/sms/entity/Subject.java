package com.dbms.sms.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="subjects")
public class Subject {
	
	@Id
	@Column(name="sub_id")
	private Long subId;
	
	@Column(name="sub_name")
	private String sub_name; 
	

	
	
	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public List<Exam> getExamList() {
		return examList;
	}

	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}

	@OneToMany(mappedBy = "subId", cascade = CascadeType.ALL)
    private List<Teacher> teacherList;
	
	@OneToMany(mappedBy = "subId", cascade = CascadeType.ALL)
    private List<Exam> examList;
}
