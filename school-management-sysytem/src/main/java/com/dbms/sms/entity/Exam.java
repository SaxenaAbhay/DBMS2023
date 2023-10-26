package com.dbms.sms.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="exam")
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exam_id")
	private Long examId;
		
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
	
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public List<Score> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<Score> scoreList) {
		this.scoreList = scoreList;
	}

	public Subject getSubId() {
		return subId;
	}

	public void setSubId(Subject subId) {
		this.subId = subId;
	}

	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
	 private List<Score> scoreList;
	 
	@ManyToOne
	@JoinColumn(name = "subject_id", referencedColumnName = "sub_id")
	private Subject subId;



	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}
}
