package com.sgi.springtraining.rest.controller;

import com.sgi.springtraining.rest.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.sgi.springtraining.rest.model.Book;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    Logger logger = LoggerFactory.getLogger(InfoController.class);
    BookRepository repo;

    public BookController(){
        repo = new BookRepository();
    }

    @GetMapping("/books")
    List<Book> all() {
        List<Book> returnList = repo.listBooks();
        return returnList;
    }

    // Add a book
    @PostMapping("/books")
    Book newBook(@RequestBody Book newBook) {
        logger.info("Add new book "+newBook.toString());
        return repo.addBook(newBook);
    }

    // Show a book
    @GetMapping("/books/{id}")
    Book one(@PathVariable Long id) {
        logger.info("Get book with id "+id);
        return repo.getBook(id);
    }

    // Update a book
    @PutMapping("/books/{id}")
    Book replaceBook(@RequestBody Book updateBook, @PathVariable Long id) {
        logger.info("Put book with id "+id);
        return repo.updateBook(id, updateBook);
    }

    // Delete a book
    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Long id) {
        repo.deleteBook(id);
    }
}
