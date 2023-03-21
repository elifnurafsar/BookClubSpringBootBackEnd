package com.BookClub.BookClub.Services.Book;

import com.BookClub.BookClub.Entities.Books.Publisher;
import com.BookClub.BookClub.Mapper.Book.PublisherMapper;
import com.BookClub.BookClub.Repositories.Book.PublisherRepository;
import com.BookClub.BookClub.Response.Book.PublisherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository _publisherRepository) {
        this.publisherRepository = _publisherRepository;
    }

    public List<PublisherResponse> getAllPublishers(){
        List<Publisher> p_list = publisherRepository.findAll();
        return PublisherMapper.INSTANCE.entityListToResponseList(p_list);
    }

    public List<Publisher> getAllPublishersWithID(){
        List<Publisher> p_list = publisherRepository.findAll();
        return p_list;
    }

    public Publisher getPublisherWithIDByID(UUID id){
        Publisher p = publisherRepository.findById(id).orElse(null);
        if(p != null){
            return p;
        }
        else
            return null;
    }

    public PublisherResponse getPublisherByID(UUID id){
        //or convert uuid to string
        Publisher p = publisherRepository.findById(id).orElse(null);
        if(p != null){
            return PublisherMapper.INSTANCE.entityToResponse(p);
        }
        else
            return null;
    }

    public PublisherResponse insertPublisher(Publisher new_publisher){
        int i = publisherRepository.insertPublisher(new_publisher.getName(), new_publisher.getWebsite(), new_publisher.getPhone(), new_publisher.getAddress());
        if(i>0){
            return PublisherMapper.INSTANCE.entityToResponse(new_publisher);
        }
        return null;
    }

    public PublisherResponse UpdatePublisherByID(UUID id, Publisher new_publisher){
        //business logic
        Optional<Publisher> _publisher = publisherRepository.findById(id);
        if(_publisher.isPresent()){
            new_publisher.setID(id);
            publisherRepository.save(new_publisher);
            return PublisherMapper.INSTANCE.entityToResponse(new_publisher);
        }
        else
            return null;
    }

    public boolean deletePublisherByID(UUID id){
        publisherRepository.deleteById(id);
        Optional<Publisher> _publisher = publisherRepository.findById(id);
        if(_publisher.isPresent())
            return false;
        return true;
    }

    public List<PublisherResponse> getPublisherByName(String name) {
        name=name.toLowerCase();
        List<Publisher> _publisher = publisherRepository.findByName(name);
        if(_publisher.size()>0){
            return PublisherMapper.INSTANCE.entityListToResponseList(_publisher);
        }
        else
            return null;
    }

    public List<Publisher> getPublisherWithIDByName(String name) {
        name=name.toLowerCase();
        List<Publisher> _publisher = publisherRepository.findByName(name);
        if(_publisher.size()>0){
            return _publisher;
        }
        else
            return null;
    }

    public List<String> getAllPublisherNames() {
        List<String> publisherList = publisherRepository.getPublisherNames();
        if(publisherList.size()>0){
            return publisherList;
        }
        else
            return null;
    }
}
