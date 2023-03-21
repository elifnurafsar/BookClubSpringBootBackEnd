package com.BookClub.BookClub.DTO.Util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {
    private String username;
    private String isbn;
    private int count;
    private String address;
    private String phone;
    private long areacode;
}
