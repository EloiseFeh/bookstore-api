package com.eloisefeh.bookstore.book;

import com.eloisefeh.bookstore.book.dtos.BookRecordDto;
import com.eloisefeh.bookstore.book.dtos.BookResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<BookResponseDto>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        Page<BookResponseDto> books  = bookService.getAllBooks(pageable);
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
