package com.BookClub.BookClub.Controllers.Books;

import com.BookClub.BookClub.Entities.Books.Author;
import com.BookClub.BookClub.Exceptions.ErrorResponse;
import com.BookClub.BookClub.Response.Book.AuthorResponse;
import com.BookClub.BookClub.Services.Book.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorResponse> allAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/get-with-id")
    public List<Author> allAuthorsWithID(){
        return authorService.getAllAuthorsWithID();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable("id") UUID id){
        //or convert uuid to string
        return authorService.getAuthorByID(id);
    }

    @GetMapping("/search-by-name/{name}")
    public List<Author> getAuthorWithIDByName(@PathVariable("name") String name){
        return authorService.searchAuthorByName(name);
    }

    @GetMapping("/names")
    public List<String> getAuthorNames(){
        return authorService.getAllAuthorNames();
    }

    @GetMapping("/get-by-name/{name}")
    public List<AuthorResponse> getAuthorByName(@PathVariable("name") String name){
        return authorService.getAuthorByName(name);
    }

    @PostMapping
    public AuthorResponse createAuthor(@RequestBody Author new_author){
        return authorService.insertAuthor(new_author);
    }

    @DeleteMapping("/{id}")
    public boolean DeleteAuthor(@PathVariable("id") UUID id){
        return authorService.deleteAuthorByID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable("id") UUID id, @RequestBody Author new_author){
       AuthorResponse res = authorService.updateAuthorByID(id, new_author);
       if(res != null){
           List<String> message = new ArrayList<>();
           message.add(res.getName());
           message.add(res.getWebsite());
           message.add(res.getBiography());
           ErrorResponse response = new ErrorResponse(message);
           return new ResponseEntity<>(response, HttpStatus.OK);
       }
       else{
           List<String> message = new ArrayList<>();
           message.add("Cannot find or update this author");
           return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
       }
    }

    /*
    @DeleteMapping(value = "/{id}")
    public boolean deleteAuthor(@PathVariable("id") String id){
        int a = authorRepository.deleteAuthor(UUID.fromString(id));
        if(a>0)
            return true;
        return false;
    }
    */

}
