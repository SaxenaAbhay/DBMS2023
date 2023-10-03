package com.dbms.sms.entity;

import jakarta.persistence.*;

@Entity
@Table(name="subjects")
public class Subject {
	
	@Id
	@Column(name="Id")
	private int subjectId;
	
	@Column(name="name")
	private String subjectName;
	
	@Column(name="credits")
	private int credits;
	
	@Column(name="incharge")
	private int inchargeId;
	
	@Column(name="class")
	private int classId;
	
	
}
