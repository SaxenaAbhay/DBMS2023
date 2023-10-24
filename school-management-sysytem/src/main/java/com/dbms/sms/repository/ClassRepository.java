package com.dbms.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.repository.CrudRepository;

import com.dbms.sms.entity.Class;
public interface ClassRepository extends JpaRepository<Class, Long> {

}
