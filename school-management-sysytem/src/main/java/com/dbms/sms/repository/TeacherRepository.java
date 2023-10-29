package com.dbms.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dbms.sms.entity.Exam;
//import org.springframework.data.repository.CrudRepository;
import com.dbms.sms.entity.Teacher;
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	@Query(value = "SELECT * FROM teachers where subject_id = ?1 ", nativeQuery = true)
	  List<Teacher> findBySubjectId(Long subjectId);
}


