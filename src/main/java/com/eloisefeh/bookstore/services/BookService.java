package com.eloisefeh.bookstore.services;

import com.eloisefeh.bookstore.dtos.BookRecordDto;
import com.eloisefeh.bookstore.models.BookModel;
import com.eloisefeh.bookstore.models.PublisherModel;
import com.eloisefeh.bookstore.repositories.BookRepository;
import com.eloisefeh.bookstore.repositories.PublisherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public BookModel saveBook(BookRecordDto bookDto){
        BookModel bookModel = new BookModel();
        bookModel.setName(bookDto.name());
        bookModel.setTotalQuantity(bookDto.totalQuantity());
        bookModel.setReleaseDate(bookDto.releaseDate());

        PublisherModel publisher = publisherRepository.findById(bookDto.publisherId())
                .orElseThrow(() -> new RuntimeException("Editora não encontrada"));

        bookModel.setPublisher((publisher));

        return bookRepository.save(bookModel);
    }
}
