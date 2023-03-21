package com.BookClub.BookClub.Controllers;

import com.BookClub.BookClub.Entities.Books.NovelBook;
import com.BookClub.BookClub.Entities.Books.StoryBook;
import com.BookClub.BookClub.Entities.Books.TextBook;
import com.BookClub.BookClub.Entities.Discount;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Discount")
public class DiscountController {

    @PostMapping
    public String setDiscount(@RequestBody Discount discount){
        if(discount.getType().toLowerCase().contains("novel")){
            NovelBook.setDiscount(discount.getDiscount());
            //return true;
            return "Discount " + NovelBook.getDiscount() + " set for novels.";
        }
        else if(discount.getType().toLowerCase().contains("text")){
            TextBook.setDiscount(discount.getDiscount());
            //return true;
            return "Discount " + TextBook.getDiscount() + " set for text books.";
        }
        else if(discount.getType().toLowerCase().contains("story")){
            StoryBook.setDiscount(discount.getDiscount());
            //return true;
            return "Discount " + StoryBook.getDiscount() + " set for story books.";
        }
        else{
            //return false;
            return "Please enter the book type as one of \"text\", \"novel\" or \"story\".";
        }
    }

    @GetMapping("/text-book/")
    public int getTextBookDiscount(){
        return TextBook.getDiscount();
    }

    @GetMapping("/story-book/")
    public int getStoryBookDiscount(){
        return StoryBook.getDiscount();
    }

    @GetMapping("/novel/")
    public int getNovelDiscount(){
        return NovelBook.getDiscount();
    }

}
