package com.BookClub.BookClub.Services.Book;

import com.BookClub.BookClub.Entities.Books.Author;
import com.BookClub.BookClub.Mapper.Book.AuthorMapper;
import com.BookClub.BookClub.Repositories.Book.AuthorRepository;
import com.BookClub.BookClub.Response.Book.AuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository _authorRepository) {
        this.authorRepository = _authorRepository;
    }

    public List<AuthorResponse> getAllAuthors(){
        List<Author> list = authorRepository.findAll();
        return AuthorMapper.INSTANCE.entityListToResponseList(list);
    }

    public List<Author> getAllAuthorsWithID() {
        return authorRepository.findAll();
    }

    public Author getAuthorByID(UUID id){
        //or convert uuid to string
        Author a = authorRepository.findById(id).orElse(null);
        if(a != null){
            return a;
        }
        return null;
    }

    public List<Author> searchAuthorByName(String name){
        //or convert uuid to string
        List<Author> authorList = authorRepository.findByName(name);
        if(authorList.size() > 0){
            return authorList;
        }
        return null;
    }

    public AuthorResponse insertAuthor(Author new_author){
        int i = authorRepository.insertAuthor(new_author);
        if(i>0){
            return AuthorMapper.INSTANCE.entityToResponse(new_author);
        }
        return null;
    }

    public boolean deleteAuthorByID(UUID id){
        authorRepository.deleteById(id);
        Optional<Author> _author = authorRepository.findById(id);
        return _author.isEmpty();
    }

    public AuthorResponse updateAuthorByID(UUID id, Author new_author){
        Optional<Author> u_author = authorRepository.findById(id);
        if(u_author.isPresent()){
            Author found = u_author.get();
            found.setName(new_author.getName());
            found.setWebsite(new_author.getWebsite());
            found.setBiography(new_author.getBiography());
            authorRepository.save(found);
            return AuthorMapper.INSTANCE.entityToResponse(new_author);
        }
        else
            return null;
    }

    public List<AuthorResponse> getAuthorByName(String name) {
        name = name.toLowerCase();
        List<Author> authorList = authorRepository.findByName(name);
        if(authorList.size()>0){
            return AuthorMapper.INSTANCE.entityListToResponseList(authorList);
        }
       else
           return null;
    }

    public List<String> getAllAuthorNames() {
        List<String> authorList = authorRepository.getAuthorNames();
        if(authorList.size()>0){
            return authorList;
        }
        else
            return null;
    }

}
