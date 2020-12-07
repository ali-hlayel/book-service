package com.bookService.models;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class BookModel {

    private String title;

    private String isbn;

    private Set<AuthorModel> authors;
}
