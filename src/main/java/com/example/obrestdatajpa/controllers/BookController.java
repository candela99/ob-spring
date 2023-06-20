package com.example.obrestdatajpa.controllers;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);
    //atributos
    private BookRepository bookRepository;

    /**
    * Constructores, solo los utiliza Spring:
     * al ver que ya tiene un Bean de estilo BookRepository,
     * se lo inyecta directamente al momento de crear el Bean BookController
    **/
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /** CRUD sobre la entidad Book **/

    /**
    * Buscar todos los libros en la db:
     * - Recuperar y devolver los libros de la Base de Datos
     * - Se le agrega el /api/ para identificar que devuelve un JSON
     * - Devuelve un JSON  con los datos, lo hace parseando los objetos Java a JSON con la libreria Jackson
    **/
    @GetMapping("/api/books")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

     /**
     * Buscar solo un libro  en la db segun su ID
      * - Usamos el objeto Optional como envoltorio para no trabajar directamente con el null ya que nos puede traer errores
     **/
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

    /**
    * Crear un nuevo libro en la db
     * - No colisiona con findAll() porque son diferentes metodos HTTP,
     * - El libro devuelto tiene PK
    **/
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        if(book.getId() != null){
            log.warn("Trying to create a book with id"); //da mucha más info que un sout
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }


    /**
     * Actualizar un libro existente en la Base de Datos
     */
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book) {
        if (book.getId() == null) {
            log.warn("Trying to update a non existent book");
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    //Borrar un libro en la db
}
