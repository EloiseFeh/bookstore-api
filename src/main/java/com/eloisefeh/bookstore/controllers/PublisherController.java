package com.eloisefeh.bookstore.controllers;

import com.eloisefeh.bookstore.dtos.PublisherRecordDto;
import com.eloisefeh.bookstore.models.PublisherModel;
import com.eloisefeh.bookstore.services.PublisherService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping
    public ResponseEntity<Object> createPublisher(@RequestBody @Valid PublisherRecordDto publisherRecordDto){
        var publisherModel = new PublisherModel();
        BeanUtils.copyProperties(publisherRecordDto, publisherModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.savePublisher(publisherModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePublisher(@PathVariable Long id,
                                                  @RequestBody @Valid PublisherRecordDto publisherDto){
        Optional<PublisherModel> publisherOptional = publisherService.findById(id);
        if (!publisherOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Editora não encontrada");
        }

        var publisherModel = new PublisherModel();
        BeanUtils.copyProperties(publisherDto, publisherModel);
        publisherModel.setId(publisherOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(publisherService.savePublisher(publisherModel));
    }

    @GetMapping
    public ResponseEntity<List<PublisherModel>> getAllPublisher(){
        List<PublisherModel> publishers = publisherService.findAll();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPublisherById(@PathVariable Long id){
        Optional<PublisherModel> publisher = publisherService.findById(id);
        if (!publisher.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Editora não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(publisher.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePublisher(@PathVariable Long id){
        Optional<PublisherModel> publisher = publisherService.findById(id);

        if (!publisher.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Editora não encontrada");
        }
        publisherService.delete(publisher.get());

        return ResponseEntity.status(HttpStatus.OK).body("Editora deletada com sucesso!");
    }

}
