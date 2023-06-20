package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		//CRUD, normalmente esto se hace desde un Servicio o Controlador
		//crear un libro
		Book book = new Book(null, "El prisionero de Azkaban", "Fran", 450, "100 usd", LocalDate.of(2012, 3, 12), true);
		//almacenar un libro
		System.out.println("Num libros en db: " + repository.count());
		repository.save(book);
		//recuperar todos los libros
		System.out.println(repository.findAll());
		System.out.println("Num libros en db: " + repository.count());
		//borrar un libro

	}

}
