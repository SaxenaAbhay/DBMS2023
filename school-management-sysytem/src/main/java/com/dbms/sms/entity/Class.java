package com.dbms.sms.entity;

import jakarta.persistence.*;

@Entity
@Table(name="class")
public class Class {

	@Id
	@Column(name="classId")
	private int Id;
	
	@Column(name="teacher")
	private int teacherId;
	
	
	
}
