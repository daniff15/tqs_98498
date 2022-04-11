package com.tqs;

import java.util.Date;

public class Book {
	private final String title;
	private final String author;
	private final Date published;
 

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
