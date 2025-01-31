package com.eloisefeh.bookstore.services;

import com.eloisefeh.bookstore.dtos.PublisherRecordDto;
import com.eloisefeh.bookstore.models.PublisherModel;
import com.eloisefeh.bookstore.repositories.PublisherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public PublisherModel savePublisher(PublisherRecordDto publisherDto){
        PublisherModel publisherModel = new PublisherModel();
        publisherModel.setName(publisherDto.name());
        publisherModel.setEmail(publisherDto.email());
        publisherModel.setPhone(publisherDto.phone());
        publisherModel.setSite(publisherDto.site());

        return publisherRepository.save(publisherModel);
    }

    public List<PublisherModel> findAll() {
        return publisherRepository.findAll();
    }
}
