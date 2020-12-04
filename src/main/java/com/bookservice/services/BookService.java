package com.bookservice.services;

import com.bookservice.config.exceptions.EntityAlreadyExistsException;
import com.bookservice.entities.Book;

import javax.persistence.NoResultException;
import java.util.List;

public interface BookService {

    Book createBook(Book book) throws EntityAlreadyExistsException;

    Book getById(Long id) throws NoResultException;

    void delete(Long id) throws NoResultException;

    List<Book> getBooks(int page, int limit);

}
