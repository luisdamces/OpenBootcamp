package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Observar cómo el objeto Greeter se instancia solamente una vez
 * y posteriormente se entrega cada vez que se lo solicita al *context*
 * Esto sucede siempre que en el bean "scope=singleton", que es el
 * valor por defecto. El otro valor es "prototype" (entre varios más), en 
 * cuyo caso se instanciará un objeto nuevo por cada llamada a "context.getBean()"
 * 
 * Por el contrario, el último *greeter* se obtiene creando una nueva
 * instancia de la clase Greeter (obsérvese que se genera nuevamente el
 * mensaje de construcción de la clase)
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Cargando el contexto ...");

        // aquí se creará el objeto Greeter (instancia de clase)
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Thread.sleep(3000);
        System.out.println("Un Saludador de \"contexto\" saluda ...");

        // aquí se entrega el objeto Greeter instanciado
        Greeter greeter = (Greeter) context.getBean("greeterService");
        String saludo = greeter.sayHello();
        System.out.println(saludo);

        Thread.sleep(3000);
        System.out.println("Otro Saludador de \"contexto\" saluda ...");

        // nuevamente se entrega el objeto Greeter ya instanciado
        Greeter greeter2 = (Greeter) context.getBean("greeterService");
        String saludo2 = greeter2.sayHello();
        System.out.println(saludo2);
        
        Thread.sleep(3000);
        System.out.println("Un Saludador Instanciado explícitamente saluda ...");

        // se vuelve a crear una instancia de la clase Greeter
        Greeter greeter3 = new Greeter();
        String saludo3 = greeter3.sayHello();
        System.out.println(saludo3);

        // La clase ApplicationContext no tiene el método close(), por lo
        // tanto tengo que hacer un "cast" a ClassPathXmlApplicationContext
        ((ClassPathXmlApplicationContext) context).close();
    }
}