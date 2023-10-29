package com.dbms.sms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import org.springframework.data.repository.CrudRepository;

import com.dbms.sms.entity.Score;
public interface ScoreRepository extends JpaRepository<Score, Long> {
	
	@Query(value = "SELECT * FROM scores where exam_id = ?1 ", nativeQuery = true)
	  List<Score> findByExamId(Long examId);
}
