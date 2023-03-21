package com.BookClub.BookClub.Entities.Books;

import org.springframework.stereotype.Component;

@Component
public class BookFactory {

    public Book getBook(Book book) {
        if ("novel".equalsIgnoreCase(book.getType())) {
            //double _price = book.getPrice() - (book.getPrice() * NovelBook.getDiscount() / 100);
            NovelBook novel = new NovelBook(book.getIsbn(), book.getTitle(), book.getGenre(), book.getYear(), book.getPrice(), book.getType(), book.getAuthor(), book.getPublisher(), book.getDescription());
            return novel;
        }
        else if ("text book".equalsIgnoreCase(book.getType())) {
            //double _price = book.getPrice() - (book.getPrice() * TextBook.getDiscount() / 100);
            TextBook textBook = new TextBook(book.getIsbn(), book.getTitle(), book.getGenre(), book.getYear(), book.getPrice(), book.getType(), book.getAuthor(), book.getPublisher(), book.getDescription());
            return textBook;
        }
        else if("story book".equalsIgnoreCase(book.getType())) {
            //double _price = book.getPrice() - (book.getPrice() * StoryBook.getDiscount() / 100);
            StoryBook storyBook = new StoryBook(book.getIsbn(), book.getTitle(), book.getGenre(), book.getYear(), book.getPrice(), book.getType(), book.getAuthor(), book.getPublisher(), book.getDescription());
            return storyBook;
        }
        else
            return book;
    }
}
