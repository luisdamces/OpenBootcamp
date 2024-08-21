# <sg>Clase 03 - Curso de Spring</sg>

## <sg>Spring Data JPA</sg>

<sg>Spring Data JPA trabaja sobre otras tecnologías, cuya jerarquía podemos ver a continuación:

```
Spring Data JPA
└── JPA
    └── Hibernate
        └── JDBC
```

### <sg>Configuración</sg>
<sg>Desde [https://start.spring.io/](https://start.spring.io/) configuramos el proyecto
de la siguiente forma:</sg>

* <sg>**Project:** Maven</sg>
* <sg>**Language:** Java</sg>
* <sg>**Spring Boot:** seleccionamos una versión estable, en ete momento la **3.3.2**</sg>
* <sg>**Metadata:** configuramos los datos que mejor nos parezca</sg>
* <sg>**Packaging:** en este caso usaremos **Jar**</sg>
* <sg>**Java version:** usaremos la versión 21 (esto irá cambiando en el tiempo)</sg>
* <sg>**Dependencies:**</sg>
  * <sg>H2 Database</sg>
  * <sg>Spring Data JPA</sg>

<sg>Por último descargamos el proyecto comprimido, lo descomprimimos donde queramos y lo
abrimos con nuestro editor favorito. Cargamos todas las dependencias de Maven y comenzamos
a trabajar en él.</sg>

## <sg>Relación entre Objeto y la BBDD

<sg>Le Indicamos a **Spring** que determinada **clase** es una <y>@Entity</y>, y eso 
será una **tabla** en la BBDD (en nuestro ejemplo es la clase **Coche**).</sg>

<sg>Luego, definimos los atributos de la clase "Entity", que serán las columnas de la tabla
definida con <y>@Entity</y>. Es importante señalar que hay un atributo especial que
corresponde al índice de la tabla, el cuál también debe llevar **annotations** que le
indiquen a Spring que es el índice. Este atributo debe ser de un **tipo objeto** porque
debe poder aceptar como valor _null_. Generalmente es de tipo **Long**.</sg>

<sg>Este es un ejemplo genérico de código:</sg>

```Java
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ClaseTablaBBDD {
    // Attributes
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String anotherAttrCol;
    
    // Constructors
  
    // Methods
}
```

<sg>Entonces, la relación entre Objeto Y BBDD quedaría como sigue:</sg>


| <sb>BBDD</sb>       | <sb>Objeto</sb>          | <sb>Annotations</sb>             |
|---------------------|--------------------------|----------------------------------|
| <sg>Tabla</sg>      | <sg>Clase</sg>           | <y>@Entity</y>                   |
| <sg>Col. Index</sg> | <sg>Atributo (Long)</sg> | <y>@Id<br/>@GeneratedValue()</y> |
| <sg>Columna</sg>    | <sg>Atributo</sg>        | <sg>Otros</sb>                   |

## <sg>Repositorio</sg>

<sg>Posteriormente debemos crear el **repositorio** para nuestro Objeto-Entidad (tabla
en la BBDD). Se hace con una **interfaz** y la **annotation** <y>@Repository</y> que
indica que la interfaz es un repositorio para la clase <y>@Entity</y> correspondiente.
A su vez, la interfaz debe "extender" a **JpaRepository**.</sg>

<sg>Un ejemplo genérico de código es el siguiente:</sg>

```Java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaseTablaBBDDRepository extends JpaRepository<ClaseTablaBBDD, Long> { }
```

[IMPORTANTE]: # (Configurar Spring para que haga uso de una BBDD en el Disco
                 y no en Memoria)

---

<style>
    y {color: yellow}
	r {color: #C33}
	v {color: violet}
	sb {color: steelblue}
	sg {color: #6CB52D} /* Spring Green */
</style>