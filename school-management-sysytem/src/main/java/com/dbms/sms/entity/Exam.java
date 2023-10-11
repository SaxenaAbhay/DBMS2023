package com.dbms.sms.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="exam")
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exam_id")
	private int examId;
		
	@Column(name="date")
	private String date;
	
	@Column(name="slot_no")
	private int slot;
	
	@Column(name="exam_type")
	private String type;
	
	@Column(name="standard")
	private int standard;
	
	@Column(name="total_marks")
	private int totalMarks;
	
	 @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
	 private List<Score> scoreList;
	 
	@ManyToOne
	@JoinColumn(name = "subject_id", referencedColumnName = "sub_id")
	Subject subId;
}
