package com.spring_security_exmp.Address_Person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring_security_exmp.Address_Person.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
