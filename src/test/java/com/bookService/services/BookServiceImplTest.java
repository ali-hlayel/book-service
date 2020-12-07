package com.bookService.services;

import com.bookService.config.exceptions.EntityAlreadyExistsException;
import com.bookService.entities.Book;
import com.bookService.model.TestBookFactory;
import com.bookService.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void testCreate() throws EntityAlreadyExistsException {
        Book book = TestBookFactory.createNewBook();
        when(bookRepository.existsByIsbn("978-3-929526-96-7")).thenReturn(false);
        doAnswer(returnsFirstArg()).when(bookRepository).save(any(Book.class));
        Book result = bookService.create(book);
        assertEquals(book.getIsbn(), result.getIsbn());
        assertEquals(book.getTitle(), result.getTitle());
    }

    @Test
    void testCreateThrowsEntityAlreadyExistsException() {
        Book book = TestBookFactory.createNewBook();
        when(bookRepository.existsByIsbn(any(String.class))).thenReturn(true);
        assertThrows(EntityAlreadyExistsException.class, () -> bookService.create(book));
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
    void testGetByIsbnThrowsNoResultException() {
        when(bookRepository.findByIsbn(any(String.class))).thenReturn(null);
        Assertions.assertThrows(NoResultException.class, () -> bookService.getByIsbn("12"));
    }

    @Test
    void testDelete() {
        Book book = TestBookFactory.createNewBook();
        book.setId(1L);
        when(bookRepository.findByIsbn(any(String.class))).thenReturn(book);
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