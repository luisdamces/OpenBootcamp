package com.example.springboot_rest_jpa.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Users")
@ApiModel("User Model")
@SuppressWarnings("unused")
public class Person {

    // Attributes <==> Columns DDBB
    @ApiModelProperty("Clave primaria para la BBDD (Long)")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key

    @ApiModelProperty("Nombre del usuario")
    private String name;
    @ApiModelProperty("Correo electrónico del usuario")
    private String email;
    @ApiModelProperty("Fecha de registro del usuario (DD-MM-AAAA)")
    private LocalDate registration;
    @ApiModelProperty("Bloqueo del usuario (true or false)")
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
