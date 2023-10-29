package com.dbms.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import org.springframework.data.repository.CrudRepository;

import com.dbms.sms.entity.Exam;
import com.dbms.sms.entity.Score;
public interface ExamRepository extends JpaRepository<Exam, Long> {
	@Query(value = "SELECT * FROM exam where subject_id = ?1 ", nativeQuery = true)
	  List<Exam> findBySubjectId(Long subjectId);
}
