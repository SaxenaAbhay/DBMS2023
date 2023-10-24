package com.dbms.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.repository.CrudRepository;

import com.dbms.sms.entity.Subject;
public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
