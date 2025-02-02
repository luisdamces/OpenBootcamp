package com.example.springboot_rest_jpa.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.example.springboot_rest_jpa.entity.Person;
import com.example.springboot_rest_jpa.repository.PersonRepository;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import springfox.documentation.annotations.ApiIgnore;


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
    @ApiOperation("Crea en BBDD un usuario nuevo con los datos enviados en el _body_")
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
            return ResponseEntity
                    .notFound()
                    .build()
                    ;
        }
    }

    // Recovery
    // 1. ALL users
    @ApiOperation("Devuelve una lista con todos los usuarios almacenados en la BBDD")
    @GetMapping("/api/v1/users")
    public List<Person> findAll() {
        return repository.findAll();
    }

    // 2. One User by Id
    @ApiOperation("Devuelve el usuario con el {Id} pasado al endpoint")
    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<Person> findById(@ApiParam("Id del usuario en la BBDD") @PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("El usuario no existe");
                    return ResponseEntity
                            .notFound()
                            .build()
                            ;
                })
//                .orElse(ResponseEntity.badRequest().build())
                ;

        // Optional<Person> personOpt = repository.findById(id);

        // if (personOpt.isEmpty()) {
        //     logger.warn("El usuario no existe");
        //     return ResponseEntity.notFound().build();
        // }

        // return ResponseEntity.ok(personOpt.get());
    }

    // 3. Users by Name
    @ApiOperation("Devuelve el usuario con el {name} pasado al endpoint")
    @GetMapping("/api/v1/users/by-name/{name}")
    public List<Person> findByName(@ApiParam("Nombre del usuario en la BBDD") @PathVariable String name) {
        return repository
                .findAll()
                .stream()
                .filter(user -> user.getName().equals(name))
                .toList()
                ;
    }

    // 4. Blocked Users
    @ApiOperation("Devuelve una lista con todos los usuarios bloqueados")
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
    @ApiOperation("Actualiza los campos de datos de un usuario determinado")
    @PutMapping("/api/v1/users/{id}")
    public ResponseEntity<Person> update(
            @ApiParam("Id del usuario en la BBDD") @PathVariable Long id,
            @RequestBody Person person
    ) {
        // Estructuras de guarda
        if (id == null) {
            logger.warn("(PUT) User Id nulo");
            return ResponseEntity
                    .badRequest()
                    .build()
                    ;
        }
        if (repository.findById(id).isEmpty()) {
            logger.warn("(PUT) User Id inexistente");
            return ResponseEntity
                    .notFound()
                    .build()
                    ;
        }

        try {
            Person existentPerson = repository.getById(id);

            // Solamente permito modificar los campos "name", "email" y "blocked"
            existentPerson.setName(person.getName());
            existentPerson.setEmail(person.getEmail());
            existentPerson.setBlocked(person.getBlocked());
            repository.flush();

            logger.info("Se ha actualizado un usuario");
            return ResponseEntity
                    .status(204)
                    .build()
                    ;
        } catch (Exception e) {
            logger.error("Hubo un error intentando actualizar un usuario", e);
            return ResponseEntity
                    .notFound()
                    .build()
                    ;
        }
    }

    // Delete
    @ApiIgnore  // le digo a Swagger que no muestre este endpoint
    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<Person> delete(@PathVariable Long id) {
        // Estructuras de guarda
        if (id == null) {
            logger.warn("(DELETE) User Id nulo");
            return ResponseEntity
                    .badRequest()
                    .build()
                    ;
        }
        if (repository.findById(id).isEmpty()) {
            logger.warn("(DELETE) User Id inexistente");
            return ResponseEntity
                    .notFound()
                    .build()
                    ;
        }

        try {
            repository.deleteById(id);
            logger.info("¡Un usuario fue borrado!");
//             return ResponseEntity.status(204).build();
            return ResponseEntity
                    .noContent()  // esta es otra forma de devolver un "Status Code 204"
                    .build()
                    ;
        } catch (Exception e) {
            logger.error("Error borrando usuario", e);
            return ResponseEntity
                    .notFound()
                    .build()
                    ;
        }
    }
}
