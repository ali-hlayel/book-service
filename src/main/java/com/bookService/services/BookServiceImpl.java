package com.bookService.services;

import com.bookService.config.exceptions.EntityAlreadyExistsException;
import com.bookService.entities.Author;
import com.bookService.entities.Book;
import com.bookService.models.BookModel;
import com.bookService.repositories.AuthorRepository;
import com.bookService.repositories.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Resource
    private BookRepository bookRepository;

    @Resource
    private AuthorRepository authorRepository;

    @Transactional
    @Override
    public Book create(BookModel bookModel) throws EntityAlreadyExistsException {
        Book book = new Book();
        Book result;
        if (!bookRepository.existsByIsbn(bookModel.getIsbn())) {
            book = bookModelMapper(bookModel);
            result = bookRepository.save(book);
        } else
            throw new EntityAlreadyExistsException("The Isbn  " + book.getIsbn() + " is already exists.");
        return result;
    }

    @Override
    public Book getByIsbn(String isbn) throws NoResultException {
        Book result = bookRepository.findByIsbn(isbn);

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

    public Book bookModelMapper(BookModel bookModel) {
        Book book = new Book();
        BeanUtils.copyProperties(bookModel, book);
        bookModel.getAuthors().stream().forEach(author -> {
            Author bookAuthor = new Author();
            bookAuthor.setFirstName(author.getFirstName());
            bookAuthor.setLastName(author.getLastName());
            book.getAuthors().add(bookAuthor);
        });
        return book;
    }

}
