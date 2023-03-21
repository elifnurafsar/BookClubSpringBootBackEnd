package com.BookClub.BookClub.Response.Book;

import lombok.Data;

@Data
public class PublisherResponse {
    //response entity to hide unnecessary info
    private String name;
    private String website;
    private String phone;
    private String address;

}
