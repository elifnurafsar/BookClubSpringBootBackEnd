package com.BookClub.BookClub.Services.User;

import com.BookClub.BookClub.Entities.User.Authorities;
import com.BookClub.BookClub.Mapper.User.AuthoritiesMapper;
import com.BookClub.BookClub.Repositories.User.AuthorityRepository;
import com.BookClub.BookClub.Response.User.AuthorityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityService(AuthorityRepository _authorityRepository) {
        this.authorityRepository = _authorityRepository;
    }

    public AuthorityResponse myAccount(String username) {
        Optional<Authorities> authorities = authorityRepository.getAuthority(username);
        if(authorities.isPresent()){
            AuthorityResponse authorityResponse = AuthoritiesMapper.INSTANCE.entityToResponse(authorities.get());
            return authorityResponse;
        }
        else{
            return null;
        }
    }
}
