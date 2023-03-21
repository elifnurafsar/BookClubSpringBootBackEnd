package com.BookClub.BookClub.Controllers.User;


import com.BookClub.BookClub.DTO.User.UserDTO;
import com.BookClub.BookClub.Response.User.UserResponse;
import com.BookClub.BookClub.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService _userService) {
        this.userService = _userService;
    }

    @GetMapping
    public List<UserResponse> getAllLocations(){
        return userService.getAll();
    }

    @GetMapping("/get-by-e_mail/{e_mail}")
    public UserResponse getUserByEMail(@PathVariable("e_mail") String e_mail){
        return userService.getUserByUserName(e_mail);
    }

    @GetMapping("/get-by-role/{role}")
    public List<UserResponse> getUserByRole(@PathVariable("role") String role){
        return userService.getUserByRole(role);
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserDTO new_user){
        return userService.createUser(new_user);
    }

    @DeleteMapping("/{e_mail}")
    public boolean DeleteUser(@PathVariable("e_mail") String e_mail){
        return userService.deleteUserByUserName(e_mail);
    }
}
