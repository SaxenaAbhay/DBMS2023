package com.dbms.sms.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="subjects")
public class Subject {
	
	@Id
	@Column(name="sub_id")
	private int subId;
	
	@Column(name="sub_name")
	private String sub_name; 
	
	@OneToMany(mappedBy = "subId", cascade = CascadeType.ALL)
    private List<Teacher> teacherList;
	
	@OneToMany(mappedBy = "subId", cascade = CascadeType.ALL)
    private List<Exam> examList;
}