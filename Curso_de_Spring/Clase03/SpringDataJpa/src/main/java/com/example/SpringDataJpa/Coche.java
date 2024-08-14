package com.example.SpringDataJpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Coche {
    // Attributes
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manufacturer;
    private String model;
    private Integer factoryYear;

    // Constructors
    public Coche() {
    }

    public Coche(Long id, String manufacturer, String model, Integer factoryYear) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.factoryYear = factoryYear;
    }

    // Methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getFactoryYear() {
        return factoryYear;
    }

    public void setFactoryYear(Integer factoryYear) {
        this.factoryYear = factoryYear;
    }

    // toString()
    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", factoryYear=" + factoryYear +
                '}';
    }
}
