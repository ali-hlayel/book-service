package com.bookService.model;

import com.bookService.entities.Author;
import com.bookService.entities.Book;
import com.bookService.models.AuthorModel;
import com.bookService.models.BookModel;

import java.util.HashSet;
import java.util.Set;

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
        Set<AuthorModel> authorModels = new HashSet<>();
        book.setTitle("Deutsch als Fremdsprache");
        book.setIsbn("978-3-929526-96-7");

        AuthorModel firstAuthor = new AuthorModel();
        firstAuthor.setFirstName("Anne");
        firstAuthor.setLastName("Buscha");
        authorModels.add(firstAuthor);

        AuthorModel secondAuthor = new AuthorModel();
        secondAuthor.setFirstName("Susanne");
        secondAuthor.setLastName("Raven");
        authorModels.add(secondAuthor);

        AuthorModel thirdAuthor = new AuthorModel();
        thirdAuthor.setFirstName("Gisela");
        thirdAuthor.setLastName("Linthout");
        authorModels.add(thirdAuthor);

        book.setAuthors(authorModels);

        return book;
    }
}
