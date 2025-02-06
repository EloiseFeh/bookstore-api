package com.eloisefeh.bookstore.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
    Page<BookModel> findAll(Pageable pageable);
}
