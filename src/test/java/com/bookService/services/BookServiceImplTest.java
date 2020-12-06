package com.bookService.services;

import com.bookService.config.exceptions.EntityAlreadyExistsException;
import com.bookService.entities.Book;
import com.bookService.model.TestBookFactory;
import com.bookService.models.BookModel;
import com.bookService.repositories.AuthorRepository;
import com.bookService.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void testCreate() throws EntityAlreadyExistsException {
        BookModel bookModel = TestBookFactory.bookModel();
        Book book = TestBookFactory.createNewBook();
        book.setId(1L);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book result = bookService.create(bookModel);
        assertEquals(bookModel.getIsbn(), result.getIsbn());
        verify(bookRepository).save(book);

    }

    @Test
    void testGetByIsbn() {
        Book book = TestBookFactory.createNewBook();
        book.setId(1L);
        when(bookRepository.findByIsbn(any(String.class))).thenReturn(book);
        Book result = bookService.getByIsbn(book.getIsbn());
        assertEquals(book.getIsbn(), result.getIsbn());
        verify(bookRepository).findByIsbn(book.getIsbn());
    }

    @Test
    void getByIdReturnsNoResultException() {
        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NoResultException.class, () -> bookService.getByIsbn("465-654-654"));
        verify(bookRepository).findById(1L);
    }

    @Test
    void testDelete() {
        Book book = TestBookFactory.createNewBook();
        book.setId(1L);
        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(book));
        bookService.delete(book.getIsbn());
        verify(bookRepository).delete(book);
    }

    @Test
    void testGetBooks() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Book firstBook = TestBookFactory.createNewBook();
        firstBook.setId(1L);
        Book secondBook = TestBookFactory.createNewBook();
        secondBook.setId(2L);
        secondBook.setIsbn("New One");
        List<Book> bookList = new ArrayList<>();
        bookList.add(firstBook);
        bookList.add(secondBook);
        Page<Book> booksPage = new PageImpl<>(bookList, pageRequest, bookList.size());
        when(bookRepository.findAll(pageRequest)).thenReturn(booksPage);
        List<Book> results = bookService.getBooks(0, 2);
        assertEquals(2, results.size());
        assertEquals(firstBook.getTitle(), results.get(0).getTitle());
        assertEquals(secondBook.getTitle(), results.get(1).getTitle());
        verify(bookRepository).findAll(pageRequest);
    }
}