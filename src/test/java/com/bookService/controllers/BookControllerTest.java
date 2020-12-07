package com.bookService.controllers;

import com.bookService.config.exceptions.GlobalExceptionHandler;
import com.bookService.entities.Book;
import com.bookService.model.TestBookFactory;
import com.bookService.models.BookModel;
import com.bookService.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class BookControllerTest {

    @Mock
    private BookService bookService;

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private final String LINK = "/book-service";


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new BookController(bookService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
        this.mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    }
    @Test
    void testCreateBook() throws Exception {
        BookModel bookModel = TestBookFactory.bookModel();
        when(bookService.create(any(BookModel.class))).thenReturn(TestBookFactory.createNewBook());
        this.mockMvc.perform(post(LINK + "/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json(bookModel)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetBook() {
    }

    @Test
    void testGetBooks() throws Exception {
        Book firstBook = TestBookFactory.createNewBook();
        firstBook.setId(1L);
        Book secondBook = TestBookFactory.createNewBook();
        secondBook.setId(2L);
        secondBook.setIsbn("123-123");

        List<Book> books = Arrays.asList(firstBook, secondBook);
        when(bookService.getBooks(0, 2)).thenReturn(books);
        this.mockMvc.perform(get(LINK + "/books"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBook() throws Exception {
    }

    private String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}