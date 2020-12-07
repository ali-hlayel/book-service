package com.bookService.models;

import com.bookService.entities.Author;
import com.bookService.entities.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BookModelMapper {

    public Book bookModelToBookEntity(BookModel bookModel) {
        Book book = new Book();
        BeanUtils.copyProperties(bookModel, book);
        for (AuthorModel authorModel : bookModel.getAuthors()) {
            Author author = new Author();
            BeanUtils.copyProperties(authorModel, author);
            book.getAuthors().add(author);
        }
        return book;
    }
}
