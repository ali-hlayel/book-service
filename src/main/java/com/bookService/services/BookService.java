package com.bookService.services;

import com.bookService.config.exceptions.EntityAlreadyExistsException;
import com.bookService.entities.Book;
import com.bookService.models.BookModel;

import javax.persistence.NoResultException;
import java.util.List;

public interface BookService {

    Book create(BookModel bookModel) throws EntityAlreadyExistsException;

    Book getByIsbn(String isbn) throws NoResultException;

    void delete(String isbn) throws NoResultException;

    List<Book> getBooks(int page, int limit);


}
