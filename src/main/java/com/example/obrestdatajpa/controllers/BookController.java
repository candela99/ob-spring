package com.example.obrestdatajpa.controllers;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repositories.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    //atributos
    private BookRepository bookRepository;

    /*constructores, solo los utiliza Spring:
    al ver que ya tiene un Bean de estilo BookRepository,
    se lo inyecta directamente al momento de crear el Bean BookController*/
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //CRUD sobre la entidad Book
    //Buscar todos los libros en la db
    @GetMapping("/api/books")   //se le agrega el /api/ para identificar que devuelve solo un JSON
    public List<Book> findAll() {
        //recuperar y devolver los libros de base de datos
        return bookRepository.findAll();    //devuelve un JSON  con los datos, lo hace parseando los objetos a JSON con la libreria Jackson
    }

    //Buscar solo un libro  en la db segun su ID
    /*usamos el objeto opcional como envoltorio para no trabajar directamente con el null ya que
    nos puede traer errores*/
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id) {
        //option 1
        Optional<Book> bookOpt = bookRepository.findById(id);
        if(bookOpt.isPresent()){
            return ResponseEntity.ok(bookOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //Crear un nuevo libro en la db
    @PostMapping("/api/books")
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    //Actualizar un libro existente en la db

    //Borrar un libro en la db
}
