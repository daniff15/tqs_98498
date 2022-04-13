package com.tqs;

import java.util.Date;

public class Book {
	private String title;
	private String author;
	private Date published;
 
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, Date published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public String getTitle() {
        return this.title;
    }


    public String getAuthor() {
        return this.author;
    }


    public Date getPublished() {
        return this.published;
    }

}
