package com.sgi.springtraining.rest.controller;

import com.sgi.springtraining.rest.model.Book;
import com.sgi.springtraining.rest.repository.BookRepository;
import com.sgi.springtraining.rest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
        try {
            List<Book> books = new ArrayList<Book>();

            if (title == null)
                bookRepository.findAll().forEach(books::add);
            else
                bookRepository.findByTitleContaining(title).forEach(books::add);

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Optional<Book> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        try {
            Book _book = bookRepository
                    .save(new Book(book.getTitle(), book.getPrice()));
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        Optional<Book> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setTitle(book.getTitle());
            _book.setPrice(book.getPrice());
            return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/books")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            bookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
