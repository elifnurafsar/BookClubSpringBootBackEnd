package com.BookClub.BookClub.Controllers.Books;

import com.BookClub.BookClub.DTO.Book.BookDTO;
import com.BookClub.BookClub.Entities.Books.Book;
import com.BookClub.BookClub.Response.Book.BookResponse;
import com.BookClub.BookClub.Services.Book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponse> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/recent")
    public List<BookResponse> getRecentBooks(){
        return bookService.getRecentBooks();
    }

    @GetMapping("/{ISBN}")
    public BookResponse getBookByISBN(@PathVariable("ISBN") String ISBN){
        return bookService.getBookByISBN(ISBN);
    }

    @GetMapping("/get-by-title-or-type/{title_or_type}")
    public List<BookResponse> getBookByTitleOrType(@PathVariable("title_or_type") String title_or_type){
        return bookService.findByTitleOrType(title_or_type);
    }

    @GetMapping("/get-myBasket/{ISBNs}")
    public List<BookResponse> getBooksInMyBasket(@PathVariable("ISBNs") String ISBNs){
        return bookService.getBooksInMyBasket(ISBNs);
    }

    @PostMapping
    public boolean createBook(@RequestBody BookDTO new_book){
        return bookService.insertBook(new_book);
    }

    @DeleteMapping("/{ISBN}")
    public boolean deleteBook(@PathVariable("ISBN") String ISBN){
        return bookService.deleteBook(ISBN);
    }

    @PutMapping("/{ISBN}")
    public boolean updateBook(@PathVariable("ISBN") String isbn, @RequestBody BookDTO new_book){
        return bookService.updateBook(isbn, new_book);
    }

    @PutMapping("/update_price/{ISBN}")
    public Book updatePrice(@PathVariable("ISBN") String isbn, @RequestBody BookDTO new_book){
        return bookService.updateBookPrice(isbn, new_book);
    }

}
