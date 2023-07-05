package com.example.obrestdatajpa.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;

@Entity //crea una tabla Book con esta estructura
@Table(name = "books")
@ApiModel("Entidad libro para representar un elemento didactico compuesto por láminas de celulosa macerada en Puerto Rico")
public class Book {

    //atributos encapsulados con private
    @Id //indica que es el ID de la tabla Book
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indica que sea autoincremental
    @ApiModelProperty("Clave ficticia autoincremental tipo Long")
    private Long id;
    private String title;
    private String author;
    private Integer pages;
    @ApiModelProperty("Precio en dólares")
    private String price;
    private LocalDate releaseDate;
    private Boolean online;

    //constructores siempre uno vacío y otro con todos los parametros
    public Book() {
    }

    public Book(Long id, String title, String author, Integer pages, String price, LocalDate releaseDate, Boolean online) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.releaseDate = releaseDate;
        this.online = online;
    }

    //getter y setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    //toString

}
