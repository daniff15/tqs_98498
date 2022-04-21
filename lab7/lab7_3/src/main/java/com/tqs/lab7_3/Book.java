package com.tqs.lab7_3;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ibn;
    private String author;
    private String name;
    private String release_year;

    public Book() {

    }

    public Book(String ibn, String author, String name, String release_year) {
        this.ibn = ibn;
        this.author = author;
        this.name = name;
        this.release_year = release_year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getIbn() {
        return this.ibn;
    }

    public void setIbn(String ibn) {
        this.ibn = ibn;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_year() {
        return this.release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    @Override
    public String toString() {
        return "{" +
            " ibn='" + getIbn() + "'" +
            ", author='" + getAuthor() + "'" +
            ", name='" + getName() + "'" +
            ", release_year='" + getRelease_year() + "'" +
            "}";
    }

}