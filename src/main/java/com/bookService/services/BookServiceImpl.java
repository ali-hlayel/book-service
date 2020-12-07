package com.bookService.services;

import com.bookService.config.exceptions.EntityAlreadyExistsException;
import com.bookService.entities.Book;
import com.bookService.repositories.AuthorRepository;
import com.bookService.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Resource
    private BookRepository bookRepository;

    @Resource
    private AuthorRepository authorRepository;

    @Transactional
    @Override
    public Book create(Book book) throws EntityAlreadyExistsException {
        Book result;
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new EntityAlreadyExistsException("Can't create Book, ISBN " + book
                    .getIsbn() + " is already in exist.");
        }
            result = bookRepository.save(book);
        return result;
    }

    @Override
    public Book getByIsbn(String isbn) throws NoResultException {
        Optional<Book> optionalBook = Optional.ofNullable(bookRepository.findByIsbn(isbn));
        if (!optionalBook.isPresent()) {
            throw new NoResultException("Could not find book with ISBN " + isbn);
        }
        Book result = optionalBook.get();
        return result;
    }

    @Override
    public void delete(String isbn) throws NoResultException {
        Book existingBook = getByIsbn(isbn);
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
