package com.example.springboot_rest_jpa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.example.springboot_rest_jpa.entity.Person;
import com.example.springboot_rest_jpa.repository.PersonRepository;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;


@RestController
@SuppressWarnings("unused")
public class PersonController {

    // Attributes
    private final PersonRepository repository;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    // Constructor (obtener el repository a través del constructor facilita el testing)
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    // Methods //
    // Create
    @PostMapping("/api/v1/users")
    public ResponseEntity<Person> create(@RequestBody Person person) {

        // Decido ignorar el Id que pudiere venir en la petición
        // (hay otras formas de gestionarlo, yo elijo esta)
        person.setId(null);

        // Realmente no tiene sentido usar el try-catch ya que, aparentemente,
        // Spring gestiona todo automáticamente, es decir, si uso este try-catch
        // o no lo uso, el resultado operacional es exactamente el mismo.
        try {
            Person newPerson = repository.save(person);
            logger.info("Se agregó un nuevo usuario a la BBDD");

            URI uri = URI.create("http://localhost:8080/api/v1/users/" + newPerson.getId());
            return ResponseEntity
                    .created(uri)
                    .body(newPerson)
                    ;

            // return ResponseEntity.ok(repository.save(person));

        } catch (Exception e) {
            logger.error("¡Error tratando de crear un nuevo usuario!", e);
            return ResponseEntity.notFound().build();
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
//            .orElse(ResponseEntity.badRequest().build())
                .orElseGet(() -> {
                    logger.warn("El usuario no existe");
                    return ResponseEntity.badRequest().build();
                })
                ;

        // Optional<Person> personOpt = repository.findById(id);

        // if (personOpt.isEmpty()) {
        //     logger.warn("El usuario no existe");
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
                .filter(user -> user.getName().equals(name))
                .toList()
                ;
    }

    // 4. Blocked Users
    @GetMapping("/api/v1/users/blocked")
    public List<Person> getBlockedUsers() {
        return repository
                .findAll()
                .stream()
                .filter(Person::getBlocked)
                .toList()
                ;

    }

    // Update
    @PutMapping("/api/v1/users/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person) {

        if (repository.existsById(id)) {
            // aquí se podría elegir qué campos cambiar y cuáles no usando los
            // setters del objeto "user", para lo cual habría que recuperarlo.
            repository.save(new Person(
                    id,
                    person.getName(),
                    person.getEmail(),
                    person.getRegistration(),
                    person.getBlocked()
            ));

            logger.info("Se ha actualizado un usuario");
            return ResponseEntity.status(204).build();
        }

        logger.warn("Hubo un error intentando actualizar un usuario inexistente");
        return ResponseEntity.badRequest().build();
    }

    // Delete
    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<Person> delete(@PathVariable Long id) {

        // Estructuras de guarda
        if (id == null) {
            logger.warn("User Id nulo");
            return ResponseEntity.badRequest().build();
        }
        if (repository.findById(id).isEmpty()) {
            logger.warn("User Id inexistente");
            return ResponseEntity.notFound().build();
        }

        try {
            repository.deleteById(id);
            logger.info("¡Un usuario fue borrado!");
//             return ResponseEntity.status(204).build();
            return ResponseEntity.noContent().build(); // otra forma de devolver un "Status Code 204"
        } catch (Exception e) {
            logger.error("Error borrando usuario", e);
            return ResponseEntity.notFound().build();
        }

    }
}

