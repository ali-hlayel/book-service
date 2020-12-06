package com.bookService.controllers;

import com.bookService.config.exceptions.BadRequestException;
import com.bookService.config.exceptions.EntityAlreadyExistsException;
import com.bookService.config.exceptions.ServiceResponseException;
import com.bookService.entities.Book;
import com.bookService.models.BookModel;
import com.bookService.services.BookService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "book-service", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation("Creates new Book")
    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@Valid @RequestBody BookModel createModel) throws EntityAlreadyExistsException {
        Book result = bookService.create(createModel);
        return result;
    }

    @ApiOperation("Get Book by id")
    @GetMapping(path = "/book/{isbn}")
    public ResponseEntity<BookModel> getBook(@Min(1) @PathVariable String isbn) {
        Book result;
        BookModel book;
        try {
            result = bookService.getByIsbn(isbn);
            ModelMapper modelMapper = new ModelMapper();
            book = modelMapper.map(result, BookModel.class);

            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (NoResultException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation("Get all books")
    @GetMapping(value = "/books")
    public ResponseEntity<List<BookModel>> getBooks(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "limit", defaultValue = "10") int limit) {
        try {
            List<BookModel> results = new ArrayList<>();
            List<Book> bookList = bookService.getBooks(page, limit);
            ModelMapper modelMapper = new ModelMapper();
            for (Book book : bookList) {
                results.add(modelMapper.map(book, BookModel.class));
            }
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (NoResultException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation("Deletes a book")
    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> delete(@Min(value = 1) @PathVariable String isbn) throws ServiceResponseException {
        try {
            bookService.delete(isbn);
        } catch (NoResultException e) {
            String message = "Could not delete book with ISBN" + isbn + ": " + e.getMessage();
            LOGGER.error(message, e);
            throw new BadRequestException(message, e);
        }
        return ResponseEntity.noContent().build();
    }
}
