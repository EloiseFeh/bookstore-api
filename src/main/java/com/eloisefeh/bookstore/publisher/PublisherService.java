package com.eloisefeh.bookstore.publisher;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<PublisherModel> findAll() {
        return publisherRepository.findAll();
    }

    public Optional<PublisherModel> findById(Long id){
        return publisherRepository.findById(id);
    }

    @Transactional
    public void delete(PublisherModel publisherModel){
        publisherRepository.delete(publisherModel);
    }
}
