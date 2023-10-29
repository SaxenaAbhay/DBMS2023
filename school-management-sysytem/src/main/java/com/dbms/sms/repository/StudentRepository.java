package com.dbms.sms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//import org.springframework.data.repository.CrudRepository;

import com.dbms.sms.entity.Student;
public interface StudentRepository extends JpaRepository<Student, Long> {
//	 public Student getByScholarId(String scholarId);
//	@Query(value = "SELECT * FROM STUDENT WHERE scholar_id = ?1", nativeQuery = true)
//	  Optional<Student> findById(Long id);
	
}
