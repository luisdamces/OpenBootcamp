# <sg>Clases de la 4 a la 9 - Curso de Spring</sg>

## <sg>Spring Boot</sg>

<sg>Desarrollaremos una API REST con acceso a base de datos **H2** utilizando **Spring Boot**</sg>

### <sg>Dependencias proyecto</sg>

<sg>Crearemos un proyecto de Spring Boot desde [https://start.spring.io/](https://start.spring.io/), como hemos visto hasta ahora, incluyendo las siguientes dependencias:</sg>

1. <sg>H2 Database (base de datos)</sg>
1. <sg>Spring Data JPA (conexión con la base de datos)</sg>
1. <sg>Spring web (herramientas para la API, tomcat server, etc.)</sg>
1. <sg>Spring Boot DevTools (reinicio rápido, LiveReload, etc.)</sg>

<sg>Descargamos el proyecto comprimido, lo descomprimimos donde queramos y lo
abrimos con nuestro editor favorito. Cargamos todas las dependencias de Maven y comenzamos trabajar en él.</sg>

<sg>Crearemos una Clase de tipo Entity llamada **Person** que contendrá los datos de _usuario_ de algún
sistema con login, siguiendo el siguiente esquema:</sg>

| Identificador |  Tipo     | Descripción         |
|---------------|-----------|---------------------|
| id            | Long      | Clave Primaria BBDD |
| name          | String    | Nombre del Usuario  |
| email         | String    | Correo electrónico  |
| registration  | LocalDate | Fecha de registro   |  
| blocked       | Boolean   | Usuario bloqueado   |

> [!NOTE]
> Uso **Person** como nombre de la clase Entity (que hará las veces de tabla en la BBDD) en lugar de **User** porque por alguna razón que no he logrado dilucidar Spring Boot falla al usar ese nombre para la clase.

## Desarrollo Api Rest

* Person (Entidad) @Entity
* PersonRepository (Repositorio) @Repository
* PersonController (Controlador) @RestController or @Controller
  * CRUD
* Model Vista Controlador (MVC) 
* 


---

<style>
    y {color: yellow}
	r {color: #C33}
	v {color: violet}
	sb {color: steelblue}
	sg {color: #6CB52D} /* Spring Green */
</style>