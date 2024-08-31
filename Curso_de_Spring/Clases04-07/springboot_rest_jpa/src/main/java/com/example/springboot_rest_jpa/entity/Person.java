package com.example.springboot_rest_jpa.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Users")
@SuppressWarnings("unused")
public class Person {

    // Attributes <==> Columns DDBB
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key

    private String name;
    private String email;
    private LocalDate registration;
    private Boolean blocked;

    // Constructors
    public Person() {}

    public Person(
        Long id,
        String name,
        String email,
        LocalDate registration,
        Boolean blocked
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.registration = registration;
        this.blocked = blocked;
    }

    // Methods
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistration() {
        return registration;
    }
    public void setRegistration(LocalDate registration) {
        this.registration = registration;
    }

    public Boolean getBlocked() {
        return blocked;
    }
    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    // toString()
    @Override
    public String toString() {
        return "Person [id=" + id
            + ", name=" + name
            + ", email=" + email
            + ", registration="+ registration
            + ", blocked=" + blocked
            + "]"
            ;
    }
    
}
