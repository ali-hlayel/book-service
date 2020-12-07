package com.bookService.models;

import com.bookService.entities.Author;
import com.bookService.entities.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BookModelMapper {

    public Book bookModelToBookEntity(BookModel bookModel) {
        Book book = new Book();
        BeanUtils.copyProperties(bookModel, book);
        Set<Author> authorSet = new HashSet<>();
        for (AuthorModel authorModel : bookModel.getAuthors()) {
            Author author = new Author();
            BeanUtils.copyProperties(authorModel, author);
            authorSet.add(author);
        }
        book.setAuthors(authorSet);
        return book;
    }
}
