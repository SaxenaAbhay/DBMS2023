
package com.dbms.sms.entity;

//import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name="scores")
public class Score {
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Id
	    Long id;

	    @ManyToOne
	    @JoinColumn(name = "student_id", referencedColumnName="scholar_id")
	    Student student;

	    @ManyToOne
	    @JoinColumn(name = "exam_id",referencedColumnName="exam_id")
	    Exam exam;

	    int marks;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		public Exam getExam() {
			return exam;
		}

		public void setExam(Exam exam) {
			this.exam = exam;
		}

		public int getMarks() {
			return marks;
		}

		public void setMarks(int marks) {
			this.marks = marks;
		}


}

