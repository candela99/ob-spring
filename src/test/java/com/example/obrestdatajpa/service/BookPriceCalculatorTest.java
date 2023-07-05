package com.example.obrestdatajpa.service;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePrice() {
        //configuracion de la prueba
        BookPriceCalculator calculator = new BookPriceCalculator();
        Book book = new Book(1L, "El señor de los anillos", "Author", 1000, 49.99, LocalDate.now(), true);

        //ejecuta el comportamiento a testear
        double price = calculator.calculatePrice(book);

        //realizamos las aserciones
        assertTrue(price > 0);
        assertEquals( 57.980000000000004, price);
    }
}