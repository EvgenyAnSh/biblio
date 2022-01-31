package com.example.biblio.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.awt.*;
import java.sql.Blob;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(name = "full_text", columnDefinition = "text default ''")
    private String full_text;
    private String author;
    private String genre;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] cover;

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

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }


    public Book() {
    }

    public Book(String title, String full_text, String author) {
        this.title = title;
        this.full_text = full_text;
        this.author = author;
    }
}
