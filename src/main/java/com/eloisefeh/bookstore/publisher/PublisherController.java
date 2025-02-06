package com.eloisefeh.bookstore.publisher;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<PublisherModel>> getAllPublisher(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        Page<PublisherModel> publishers = publisherService.findAll(pageable);
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
