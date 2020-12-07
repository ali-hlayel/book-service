package com.bookService.services;

import com.bookService.config.exceptions.EntityAlreadyExistsException;
import com.bookService.entities.Book;

import javax.persistence.NoResultException;
import java.util.List;

public interface BookService {

    Book create(Book book) throws EntityAlreadyExistsException;

    Book getByIsbn(String isbn) throws NoResultException;

    void delete(String isbn) throws NoResultException;

    List<Book> getBooks(int page, int limit);


}
