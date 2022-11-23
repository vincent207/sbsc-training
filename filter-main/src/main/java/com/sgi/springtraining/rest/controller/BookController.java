package com.sgi.springtraining.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookController {

    @GetMapping("/books")
    String all() {
        return "Return list of book";
    }

    // Add a book
    @PostMapping("/books")
    String newBook() {
        return "New book created";
    }

    // Show a book
    @GetMapping("/books/{id}")
    String one(@PathVariable Long id) {
        return "Accessing book "+ id;
    }

    // Update a book
    @PutMapping("/books/{id}")
    String replaceBook(@PathVariable Long id) {
        return "Updating book "+ id;
    }

    // Delete a book
    @DeleteMapping("/books/{id}")
    String deleteBook(@PathVariable Long id) {
        return "Deleting book "+ id;
    }
}
