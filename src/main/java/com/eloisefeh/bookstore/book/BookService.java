package com.eloisefeh.bookstore.book;

import com.eloisefeh.bookstore.book.dtos.BookRecordDto;
import com.eloisefeh.bookstore.book.dtos.BookResponseDto;
import com.eloisefeh.bookstore.publisher.PublisherModel;
import com.eloisefeh.bookstore.publisher.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Transactional
    public BookResponseDto saveBook(Long id, BookRecordDto bookDto){

        BookModel bookModel = (id != null) ? bookRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Livro não encontrado"))
                : new BookModel();

        bookModel.setName(bookDto.name());
        bookModel.setTotalQuantity(bookDto.totalQuantity());
        bookModel.setReleaseDate(bookDto.releaseDate());

        PublisherModel publisher = publisherRepository.findById(bookDto.publisherId())
                .orElseThrow(() -> new RuntimeException("Editora não encontrada"));

        bookModel.setPublisher((publisher));

        BookModel savedBook = bookRepository.save(bookModel);

        return new BookResponseDto(
                savedBook.getId(),
                savedBook.getName(),
                savedBook.getTotalQuantity(),
                savedBook.getReleaseDate(),
                savedBook.getPublisher().getId()
        );
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
        bookRepository.delete(bookModel);
    }
}