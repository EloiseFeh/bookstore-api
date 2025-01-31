package com.eloisefeh.bookstore.controllers;

import com.eloisefeh.bookstore.dtos.PublisherRecordDto;
import com.eloisefeh.bookstore.models.PublisherModel;
import com.eloisefeh.bookstore.services.PublisherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping
    public ResponseEntity<PublisherModel> createPublisher(@RequestBody @Valid PublisherRecordDto publisher){
        PublisherModel createdPublisher = publisherService.savePublisher(publisher);
        return new ResponseEntity<>(createdPublisher, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PublisherModel>> getAllPublishers(){

    }
}
