package com.eloisefeh.bookstore.controllers;

import com.eloisefeh.bookstore.dtos.BookRecordDto;
import com.eloisefeh.bookstore.dtos.BookResponseDto;
import com.eloisefeh.bookstore.models.BookModel;
import com.eloisefeh.bookstore.repositories.BookRepository;
import com.eloisefeh.bookstore.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody @Valid BookRecordDto book) {
        BookResponseDto createdBook = bookService.saveBook(null, book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id,
                                                      @RequestBody @Valid BookRecordDto book) {
        BookResponseDto updatedBook = bookService.saveBook(id, book);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks(){
        List<BookResponseDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable Long id) {
        BookResponseDto book = bookService.getById(id);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id){
        Optional<BookModel> bookOptional = bookService.findById(id);

        if (!bookOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }

        bookService.delete(bookOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Livro deletado com sucesso!");
    }


}
