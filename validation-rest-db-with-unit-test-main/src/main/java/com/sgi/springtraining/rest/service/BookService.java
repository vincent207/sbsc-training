package com.sgi.springtraining.rest.service;

import com.sgi.springtraining.rest.model.Book;
import com.sgi.springtraining.rest.model.Book;
import com.sgi.springtraining.rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> allBook() {
        try {
            List<Book> books = new ArrayList<Book>();
            bookRepository.findAll().forEach(books::add);
            if (books.isEmpty()) {
                return null;
            }
            return books;
        } catch (Exception e) {
            return null;
        }
    }
    
    public Book createBook(Book book) {
        try {
            Book _book = bookRepository
                    .save(new Book(book.getTitle(), book.getPrice()));
            return _book;
        } catch (Exception e) {
            return null;
        }
    }

    public Book updateBook(Long id, Book book) {
        return book;
    }

    public Book getBook(Long id) {
        return new Book();
    }

    public Book deleteBook(Long id) {
        return new Book();
    }
}
