package com.eloisefeh.bookstore.repositories;

import com.eloisefeh.bookstore.models.PublisherModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<PublisherModel, Long> {
}
