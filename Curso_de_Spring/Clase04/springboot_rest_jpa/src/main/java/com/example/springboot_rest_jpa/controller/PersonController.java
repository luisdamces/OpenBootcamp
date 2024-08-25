package com.example.springboot_rest_jpa.controller;

import org.springframework.web.bind.annotation.*;

import com.example.springboot_rest_jpa.entity.Person;
import com.example.springboot_rest_jpa.repository.PersonRepository;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;


@RestController
public class PersonController {

    // Attributes
    PersonRepository repository;

    // Constructor (obtener el repository a través del constructor facilita el testing)
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    // Methods //
    // Create
    @PostMapping("/api/v1/users")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        
        // Ignoro el Id que pudiere venir en la petición
        // (hay otras formas de gestionarlo, yo elijo ésta)
        person.setId(null);

        // Realmente no tiene sentido usar el try-catch ya que, aparentemente,
        // Spring gestiona todo automáticamente, es decir, si uso este try-catch
        // o no lo uso, el resultado operacional es exactamente el mismo.
        try {
             Person newPerson = repository.save(person);
             URI uri = URI.create("http://localhost:8080/api/v1/users/" + newPerson.getId());
             return ResponseEntity
                     .created(uri)
                     .body(newPerson)
                     ;

            // return ResponseEntity.ok(repository.save(person));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Recovery
    // 1. ALL users
    @GetMapping("/api/v1/users")
    public List<Person> findAll() {
        return repository.findAll();
    }
    
    // 2. One User by Id
    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.badRequest().build())
        ;

        // Optional<Person> personOpt = repository.findById(id); 

        // if (personOpt.isEmpty()) {
        //     return ResponseEntity.badRequest().build();
        // }
        
        // return ResponseEntity.ok(personOpt.get());
    }
    
    // 3. Users by Name
    @GetMapping("/api/v1/users/by-name/{name}")
    public List<Person> findByName(@PathVariable String name) {
        return repository
                .findAll()
                .stream()
                .filter(p -> p.getName().equals(name))
                .toList()
                ;
    }

    // Update
    @PutMapping("/api/v1/users/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person) {

        if (repository.existsById(id)) {
            // aquí se podría elegir qué campos cambiar y cuales no
            repository.save(new Person(
                    id,
                    person.getName(),
                    person.getEmail(),
                    person.getRegistration(),
                    person.getBlocked()
            ));

            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.badRequest().build();
    }

    // Delete
    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<Person> delete(@PathVariable Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        
        return ResponseEntity.badRequest().build();
    }
}
