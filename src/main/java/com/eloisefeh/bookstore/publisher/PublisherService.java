package com.eloisefeh.bookstore.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public PublisherModel savePublisher(PublisherModel publisherModel){
        return publisherRepository.save(publisherModel);
    }

    public Page<PublisherModel> findAll(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }

    public Optional<PublisherModel> findById(Long id){
        return publisherRepository.findById(id);
    }

    @Transactional
    public void delete(PublisherModel publisherModel){
        publisherRepository.delete(publisherModel);
    }
}
