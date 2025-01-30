package com.eloisefeh.bookstore.services;

import com.eloisefeh.bookstore.dtos.BookRecordDto;
import com.eloisefeh.bookstore.dtos.BookResponseDto;
import com.eloisefeh.bookstore.models.BookModel;
import com.eloisefeh.bookstore.models.PublisherModel;
import com.eloisefeh.bookstore.repositories.BookRepository;
import com.eloisefeh.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Transactional
    public BookModel saveBook(Long id, BookRecordDto bookDto){

        BookModel bookModel = (id != null) ? bookRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Livro não encontrado"))
                : new BookModel();

        bookModel.setName(bookDto.name());
        bookModel.setTotalQuantity(bookDto.totalQuantity());
        bookModel.setReleaseDate(bookDto.releaseDate());

        PublisherModel publisher = publisherRepository.findById(bookDto.publisherId())
                .orElseThrow(() -> new RuntimeException("Editora não encontrada"));

        bookModel.setPublisher((publisher));

        return bookRepository.save(bookModel);
    }

    public List<BookResponseDto> getAllBooks(){
        return bookRepository.findAll().stream()
                .map(book -> new BookResponseDto(
                        book.getId(),
                        book.getName(),
                        book.getTotalQuantity(),
                        book.getReleaseDate(),
                        book.getPublisher().getId()
                ))
                .toList();
    }

    public BookResponseDto getById(Long id){
        BookModel book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        return new BookResponseDto(
                book.getId(),
                book.getName(),
                book.getTotalQuantity(),
                book.getReleaseDate(),
                book.getPublisher().getId()
        );
    }

    public Optional<BookModel> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public void delete(BookModel bookModel){
    bookRepository.delete(bookModel);}
}