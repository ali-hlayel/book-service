package com.bookservice.services;

import com.bookservice.config.exceptions.EntityAlreadyExistsException;
import com.bookservice.entities.Book;
import com.bookservice.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) throws EntityAlreadyExistsException {
        Book result;
        if (!bookRepository.existsByIsbn(book.getIsbn())) {
            result = bookRepository.save(book);
        } else
            throw new EntityAlreadyExistsException("The Isbn  " + book.getIsbn() + " is already exists.");
        return result;
    }

    @Override
    public Book getById(Long id) throws NoResultException {
        Book result = bookRepository.findById(id).orElseThrow(
                () -> new NoResultException("No department found with id: " + id)
        );
        return result;
    }

    @Override
    public void delete(Long id) throws NoResultException {

        Book existingBook = getById(id);
        bookRepository.delete(existingBook);
    }

    @Override
    public List<Book> getBooks(int page, int limit) {
        if (page > 0) page = page - 1;
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Book> personsPage = bookRepository.findAll(pageableRequest);
        List<Book> personsList = personsPage.getContent();
        return personsList;
    }
}
