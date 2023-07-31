package com.example.obrestdatajpa.controllers;

import com.example.obrestdatajpa.entities.Book;
import io.swagger.models.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Comprobar hola mundo desde controladores Spring REST")
    @Test
    void hello() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/hola", String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Holaaaaaa", response.getBody());
    }

    @Test
    void findAll() {
        ResponseEntity<Book[]> response = testRestTemplate.getForEntity("/api/books", Book[].class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        List<Book> books = Arrays.asList(response.getBody());
        System.out.println(books.size());;
    }

    @Test
    void findOneById() {
        ResponseEntity<Book[]> response = testRestTemplate.getForEntity("/api/books/1", Book[].class);

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode()); //ramoaa25
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); //enviar JSON
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); //recibir JSON

        String json = """
                    {
                         "title": "IT",
                         "author": "Stephen King",
                         "pages": 700,
                         "price": 250.99,
                         "releaseDate": "1999-06-25",
                         "online": false
                    }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers); //petición
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.POST, request, Book.class); //respuesta

        Book result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("IT", result.getTitle());

    }

}