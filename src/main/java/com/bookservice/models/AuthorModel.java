package com.bookservice.models;

import com.bookservice.entities.Book;
import java.util.HashSet;
import java.util.Set;

public class AuthorModel {

    private String firstName;

    private String lastName;

    private Set<Book> books = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
