
package com.dbms.sms.entity;

//import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name="scores")
public class Score {
	
	    @Id
	    Long id;

	    @ManyToOne
	    @JoinColumn(name = "student_id", referencedColumnName="scholar_id")
	    Student student;

	    @ManyToOne
	    @JoinColumn(name = "exam_id",referencedColumnName="exam_id")
	    Exam exam;

	    int marks;
}

