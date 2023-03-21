package com.BookClub.BookClub.Controllers.Books;

import com.BookClub.BookClub.Entities.Books.Publisher;
import com.BookClub.BookClub.Response.Book.PublisherResponse;
import com.BookClub.BookClub.Services.Book.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Publisher")
public class PublisherController {
    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService_) {
        this.publisherService = publisherService_;
    }

    @GetMapping
    public List<PublisherResponse> getAllPublishers(){
        return publisherService.getAllPublishers();
    }

    @GetMapping("/get-all")
    public List<Publisher> getAllPublishersWithID(){
        return publisherService.getAllPublishersWithID();
    }

    @GetMapping("/names")
    public List<String> getAllPublisherNames(){
        return publisherService.getAllPublisherNames();
    }

    @GetMapping("{id}")
    public PublisherResponse getPublisher(@PathVariable("id") UUID id){
        return publisherService.getPublisherByID(id);
    }

    @GetMapping("/get-with-id/{id}")
    public Publisher getPublisherWithID(@PathVariable("id") UUID id){
        return publisherService.getPublisherWithIDByID(id);
    }

    @GetMapping("/get-by-name/{name}")
    public List<PublisherResponse> getPublisherByName(@PathVariable("name") String name){
        return publisherService.getPublisherByName(name);
    }

    @GetMapping("/get-all-by-name/{name}")
    public List<Publisher> getPublisherWithIDByName(@PathVariable("name") String name){
        return publisherService.getPublisherWithIDByName(name);
    }

    @PostMapping
    public PublisherResponse createPublisher(@RequestBody Publisher new_publisher){
        return publisherService.insertPublisher(new_publisher);
    }

    @PutMapping("/{id}")
    public PublisherResponse updatePublisher(@PathVariable("id") UUID id, @RequestBody Publisher new_publisher){
        return publisherService.UpdatePublisherByID(id, new_publisher);
    }

    @DeleteMapping("/{id}")
    public boolean DeletePublisher(@PathVariable("id") UUID id){
        return publisherService.deletePublisherByID(id);
    }

}
