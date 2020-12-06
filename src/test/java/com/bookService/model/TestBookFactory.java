package com.bookService.model;

import com.bookService.entities.Author;
import com.bookService.entities.Book;
import com.bookService.models.AuthorModel;
import com.bookService.models.BookModel;

public class TestBookFactory {

    public static Book createNewBook() {
        Book book = new Book();
        book.setTitle("Deutsch als Fremdsprache");
        book.setIsbn("978-3-929526-96-7");

        Author firstAuthor = new Author();
        firstAuthor.setId(1L);
        firstAuthor.setFirstName("Anne");
        firstAuthor.setLastName("Buscha");
        firstAuthor.getBooks().add(book);

        Author secondAuthor = new Author();
        secondAuthor.setId(2L);
        secondAuthor.setFirstName("Susanne");
        secondAuthor.setLastName("Raven");
        secondAuthor.getBooks().add(book);

        Author thirdAuthor = new Author();
        thirdAuthor.setId(3L);
        thirdAuthor.setFirstName("Gisela");
        thirdAuthor.setLastName("Linthout");
        thirdAuthor.getBooks().add(book);

        book.getAuthors().add(firstAuthor);
        book.getAuthors().add(secondAuthor);
        book.getAuthors().add(thirdAuthor);

        return book;
    }

    public static BookModel bookModel() {
        BookModel book = new BookModel();
        book.setTitle("Deutsch als Fremdsprache");
        book.setIsbn("978-3-929526-96-7");

        AuthorModel firstAuthor = new AuthorModel();
        firstAuthor.setFirstName("Anne");
        firstAuthor.setLastName("Buscha");

        AuthorModel secondAuthor = new AuthorModel();
        secondAuthor.setFirstName("Susanne");
        secondAuthor.setLastName("Raven");

        AuthorModel thirdAuthor = new AuthorModel();
        thirdAuthor.setFirstName("Gisela");
        thirdAuthor.setLastName("Linthout");

        book.getAuthors().add(firstAuthor);

        return book;
    }
}
