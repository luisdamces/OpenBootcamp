package com.example.SpringDataJpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringDataJpaApplication.class, args);

		CocheRepository repository = context.getBean(CocheRepository.class);

		System.out.println("Guardando un coche ...");
		repository.save(new Coche(
				null,
				"Chevrolet",
				"Astra II DTI 2.0 16v",
				2003
		));

		System.out.println("Guardando otro coche ...");
		repository.save(new Coche(
				null,
				"Renault",
				"Clio Diesel RL",
				1997
		));

		System.out.println("Cantidad de coches guardados: " + repository.count());

		System.out.println("Detalle de coches guardados:");
		System.out.println(repository.findAll());
	}

}
