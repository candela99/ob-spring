package com.example.obrestdatajpa.controllers;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repositories.BookRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
      * - Además, usamos ResponseEntity para devolver más información como estado, cabeceras, etc. y no solo el objeto
     **/
    @GetMapping("/api/books/{id}")
    @ApiOperation("Buscar un libro por clave primaria con id Long")
    public ResponseEntity<Book> findOneById(@ApiParam("Clave primaria tipo Long") @PathVariable Long id) {
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
        if (book.getId() == null) { /*Si es null, el método deberia ser CREATE*/
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build(); /*400*/
        }
        if(!bookRepository.existsById(book.getId())) {  /*Si el ID no es válido*/
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();   /*404*/
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    //Borrar un libro en la db
    @DeleteMapping("/api/books/{id}")
    @ApiIgnore
    public ResponseEntity<Book> delete(@PathVariable Long id) {
        if(!bookRepository.existsById(id)) {  /*Si el ID no es válido*/
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();   /*404*/
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build(); /*noContent: 204. Todo OK y se ha borrado el contenido*/
    }


    @DeleteMapping("/api/books")
    @ApiIgnore /*Ignora este metodo en la documentación de la API Swagger*/
    public ResponseEntity<Book> deleteAll() {
        log.info("REST Request for delete all books"); /*Sirve para segur el flujo de ejecución al debuguear*/
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
