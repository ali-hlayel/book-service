package com.bookservice.models;

import com.bookservice.entities.Author;
import java.util.HashSet;
import java.util.Set;

public class BookModel {

    private String title;

    private String isbn;

    private Set<Author> authors = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
