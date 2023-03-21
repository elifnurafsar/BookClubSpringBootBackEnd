package com.BookClub.BookClub.Entities;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Discount {
    private String type;
    private int discount;

    public Discount(String text_book, int _discount) {
        this.type=text_book;
        this.discount=_discount;
    }
}