package com.example.springboot_rest_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot_rest_jpa.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
