package com.BookClub.BookClub.Services.Book;

import com.BookClub.BookClub.DTO.Book.BookDTO;
import com.BookClub.BookClub.Entities.Books.Author;
import com.BookClub.BookClub.Entities.Books.Book;
import com.BookClub.BookClub.Entities.Books.BookFactory;
import com.BookClub.BookClub.Entities.Books.Publisher;
import com.BookClub.BookClub.Mapper.Book.BookMapper;
import com.BookClub.BookClub.Repositories.Book.AuthorRepository;
import com.BookClub.BookClub.Repositories.Book.BookRepository;
import com.BookClub.BookClub.Repositories.Book.PublisherRepository;
import com.BookClub.BookClub.Response.Book.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.lang.String;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final Locale lang= new Locale("en");
    private final BookFactory factory = new BookFactory();

    @Autowired
    public BookService(BookRepository _bookRepository, AuthorRepository _authorRepository, PublisherRepository _publisherRepository) {
        this.bookRepository = _bookRepository;
        this.authorRepository = _authorRepository;
        this.publisherRepository = _publisherRepository;
    }

    public List<BookResponse> getBooks(){
        List<Book> books = bookRepository.getBooks();
        for(int i=0; i<books.size(); i++){
            Book book = factory.getBook(books.get(i));
            books.set(i, book);
        }
        return BookMapper.INSTANCE.entityListToResponseList(books);
    }

    public List<BookResponse> getRecentBooks(){
        List<Book> books = bookRepository.getRecentBooks();
        for(int i=0; i<books.size(); i++){
            Book book = factory.getBook(books.get(i));
            books.set(i, book);
        }
        return BookMapper.INSTANCE.entityListToResponseList(books);
    }

    public BookResponse getBookByISBN(String ISBN){
        Optional<Book> book = bookRepository.findBookByISBN(ISBN);
        if(book.isPresent()) {
            Book _book = factory.getBook(book.get());
            return BookMapper.INSTANCE.entityToResponse(_book);
        }
        else return null;
    }

    public List<BookResponse> getBooksInMyBasket(String isbNs) {
        isbNs = isbNs.substring(0, isbNs.length()-1);
        java.util.List<String> ISBNList =Arrays.asList(isbNs.split(","));
        List<BookResponse> list = new ArrayList<>();
        for(int i=0; i<ISBNList.size(); i++){
            BookResponse book = getBookByISBN(ISBNList.get(i));
            if(book != null){
                list.add(book);
            }
        }
        return list;
    }

    public List<BookResponse> findByTitleOrType(String title_or_type){
        List<Book> books = bookRepository.findBookByTitleOrType(title_or_type);
        for(int i=0; i<books.size(); i++){
            Book book = factory.getBook(books.get(i));
            books.set(i, book);
        }
        return BookMapper.INSTANCE.entityListToResponseList(books);
    }

    public boolean insertBook(BookDTO new_book){
        Optional<String> author_name = Optional.ofNullable(new_book.getAuthor());
        Optional<String> publisher_name = Optional.ofNullable(new_book.getPublisher());
        if(author_name.isPresent() && publisher_name.isPresent()){
            String a_name = author_name.get().toLowerCase(lang);
            String p_name = publisher_name.get().toLowerCase(lang);
            List<Author> a_list= authorRepository.findByName(a_name);
            List<Publisher> p_list = publisherRepository.findByName(p_name);
            if(a_list.size()>0 && p_list.size()>0){
                int a = bookRepository.insertBook(new_book.getIsbn(), new_book.getTitle(), a_name, p_name, new_book.getGenre(), new_book.getPrice(), new_book.getYear(), new_book.getType(), new_book.getDescription());
                return a > 0;
            }
        }
        return false;
    }

    public boolean deleteBook(String ISBN){
        bookRepository.deleteById(ISBN);
        return bookRepository.findById(ISBN).isEmpty();
    }


    public boolean updateBook(String ISBN, BookDTO new_book) {
        Optional<Book> b = bookRepository.findBookByISBN(ISBN);
        Optional<String> author_name = Optional.ofNullable(new_book.getAuthor());
        Optional<String> publisher_name = Optional.ofNullable(new_book.getPublisher());
        if(author_name.isPresent() && publisher_name.isPresent()){
            String a_name = author_name.get().toLowerCase(lang);
            String p_name = publisher_name.get().toLowerCase(lang);
            List<Author> a_list= authorRepository.findByName(a_name);
            List<Publisher> p_list = publisherRepository.findByName(p_name);
            if(a_list.size()>0 && p_list.size()>0){
                BookDTO bookDTO = new_book;
                int res = bookRepository.updateBook(bookDTO);
                if(res>0)
                    return true;
                else return false;
            }
        }
        return false;
    }


    public Book updateBookPrice(String ISBN, BookDTO new_book) {
        Optional<Book> b = bookRepository.findBookByISBN(ISBN);
        if(b.isPresent()){
            Book u_book = b.get();
            u_book.setPrice(new_book.getPrice());
            return bookRepository.save(u_book);
        }
        return null;
    }

}
