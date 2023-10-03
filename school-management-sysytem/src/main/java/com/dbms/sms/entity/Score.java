
package com.dbms.sms.entity;

//import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name="scores")
public class Score {
	
	@EmbeddedId scoreId id; 
	
	@Column(name="score")
	private Long score;
}
class scoreId{
	
	@Column(name="studId")
	private int studId;
	
	@Column(name="subjId")
	private int subjId;
}
