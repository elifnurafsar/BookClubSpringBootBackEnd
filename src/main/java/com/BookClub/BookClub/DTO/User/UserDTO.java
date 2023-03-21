package com.BookClub.BookClub.DTO.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private boolean enabled;
    private String authority;
}
