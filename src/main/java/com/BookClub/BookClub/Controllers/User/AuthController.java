package com.BookClub.BookClub.Controllers.User;

import com.BookClub.BookClub.Response.User.AuthorityResponse;
import com.BookClub.BookClub.Services.User.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:8080" })
@RestController
@RequestMapping("/Auth")
public class AuthController {

    private final AuthorityService authorityService;

    @Autowired
    public AuthController(AuthorityService _authorityService) {
        this.authorityService = _authorityService;
    }

    //https://dzone.com/articles/integrating-spring-boot-and-react-with-spring-secu-1
    @GetMapping("/Login")
    public AuthorityResponse login() {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = details.getUsername();
        AuthorityResponse authorityResponse = authorityService.myAccount(username);
        return authorityResponse;
    }

    @GetMapping("/Logout")
    public String logout() {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Logged out";
    }

    /*@PostMapping("/login")
    public AuthorityResponse login(@RequestBody UserRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        AuthorityResponse authResponse = new AuthorityResponse();
        authResponse.setUsername(username);
        authResponse.setAuthority("USER");
        return authResponse;
    }*/
}
