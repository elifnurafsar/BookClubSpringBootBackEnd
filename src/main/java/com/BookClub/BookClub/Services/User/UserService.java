package com.BookClub.BookClub.Services.User;


import com.BookClub.BookClub.DTO.User.UserDTO;
import com.BookClub.BookClub.Entities.User.User;
import com.BookClub.BookClub.Mapper.User.UserMapper;
import com.BookClub.BookClub.Repositories.User.UserRepository;
import com.BookClub.BookClub.Response.User.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository _userRepository) {
        this.userRepository = _userRepository;
    }

    @Transactional
    public UserResponse createUser(UserDTO new_user){
        new_user.setPassword(new BCryptPasswordEncoder().encode(new_user.getPassword()));
        userRepository.insertUser(new_user);
        User user = userRepository.getByEmail(new_user.getUsername()).orElse(null);
        if(user != null){
            return UserMapper.INSTANCE.identifierToResponse(new_user);
        }
        else
            return null;
    }


    public UserResponse getUserByUserName(String e_mail){
        User user = userRepository.getByEmail(e_mail).orElse(null);
        if(user != null){
            return UserMapper.INSTANCE.entityToResponse(user);
        }
        else
            return null;
    }

    public boolean deleteUserByUserName(String e_mail){
        userRepository.deleteSelf(e_mail);
        Optional<User> _admin = userRepository.getByEmail(e_mail);
        if(_admin.isPresent())
            return false;
        return true;
    }

    public List<UserResponse> getAll(){
        List<User> list = userRepository.getAll();
        return UserMapper.INSTANCE.entityListToResponseList(list);
    }

    public List<UserResponse> getUserByRole(String role) {
        List<User> list = userRepository.getAllByRole(role);
        return UserMapper.INSTANCE.entityListToResponseList(list);
    }
}
