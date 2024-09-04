# <sg>Clases de la 4 a la 9 - Curso de Spring</sg>

# <sg>Spring Boot</sg>

<sg>Desarrollaremos una API REST con acceso a base de datos **H2** utilizando **Spring Boot**</sg>

## <sg>Dependencias proyecto</sg>

<sg>Crearemos un proyecto de Spring Boot desde [https://start.spring.io/](https://start.spring.io/), como hemos visto hasta ahora, incluyendo las siguientes dependencias:</sg>

1. <sg>H2 Database (base de datos)</sg>
2. <sg>Spring Data JPA (conexión con la base de datos)</sg>
3. <sg>Spring web (herramientas para la API, tomcat server, etc.)</sg>
4. <sg>Spring Boot DevTools (reinicio rápido, LiveReload, etc.)</sg>

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
> Uso **Person** como nombre de la clase Entity (que hará las veces de tabla en la BBDD) en lugar de **User** porque
> por alguna razón que no he logrado dilucidar Spring Boot falla al usar ese nombre para la clase.

<sg>Posteriormente crearemos el _Repository_ para **Person** como hemos visto anteriormente. A continuación crearemos
un _Controller_, que no es más que una clase con anotaciones especiales a la que llamaremos **PersonController** y
estará alojada en un sub paquete llamado **controller**. Este _controller_ puede ser usado en un diseño MVC, en cuyo
caso utilizaremos la _annotation_ **@Controller** para la clase, o bien, **@RestController** en el caso de que estemos
desarrollando una **API REST**.</sg>

<sg>En este caso, por simplificar, meteremos toda la lógica dentro del _controller_, pero en general lo que se haría
es crear una capa de servicios y meter allí la lógica que sería utilizada por los controladores.</sg>

<sg>La estructura general que se suele utilizar para organizar el proyecto es agrupar las distintas capas en distintos
paquetes (directorios) dentro del _root_ del mismo. A continuación vemos un ejemplo de dicha estructura:</sg>

```
root
 ├── controller
 ├── entity
 ├── repository
 └── service
```

> [!NOTE]
> Aclarar que esta estructura no es un estándar ni nada por el estilo. En Java y Spring se suele seguir este modelo,
> pero se podría agrupar como mejor nos pareciese de cara al tipo de desarrollo.

## <sg>El controlador</sg>

<sg>Por convención de nombres, al igual que en los otros casos, se utiliza un nombre "compuesto" conformado por el
nombre del modelo o entidad seguido de la palabra _Controller_ (siempre usando PascalCase) para nombrar a la clase.</sg>



<sg></sg>
<sg></sg>
<sg></sg>

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