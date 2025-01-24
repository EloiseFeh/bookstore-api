package com.eloisefeh.bookstore.controllers;

import com.eloisefeh.bookstore.dtos.BookRecordDto;
import com.eloisefeh.bookstore.models.BookModel;
import com.eloisefeh.bookstore.repositories.BookRepository;
import com.eloisefeh.bookstore.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookModel> createBook(@RequestBody @Valid BookRecordDto book){
        BookModel createdBook = bookService.saveBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }
}
