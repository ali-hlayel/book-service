package com.bookService.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class BookModel {

    @NotBlank
    @ApiModelProperty(required = true, example = "Deutsch al fremdsprache")
    private String title;

    @NotBlank
    @ApiModelProperty(required = true, example = "987-789-345")
    private String isbn;

    @NotNull
    @ApiModelProperty(required = true)
    private Set<AuthorModel> authors;
}
