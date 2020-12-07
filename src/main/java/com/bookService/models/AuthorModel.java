package com.bookService.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthorModel {

    @NotBlank
    @ApiModelProperty(required = true, example = "Ali")
    private String firstName;

    @NotBlank
    @ApiModelProperty(required = true, example = "Hlayel")
    private String lastName;
}
