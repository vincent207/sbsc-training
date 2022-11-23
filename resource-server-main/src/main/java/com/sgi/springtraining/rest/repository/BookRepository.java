package com.sgi.springtraining.rest.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.sgi.springtraining.rest.model.Book;

public class BookRepository {
    private static List<Book> books;
    private static Long idCounter = Long.valueOf(0);

    public BookRepository(){
        books = new ArrayList<>();
        idCounter++;
        books.add(new Book(idCounter,"Laskar Pelangi",Long.valueOf(100000)));
        idCounter++;
        books.add(new Book(idCounter,"Atambua: Cukup Luka Itu Yang Terlahir",Long.valueOf(95000)));
        idCounter++;
        books.add(new Book(idCounter,"Disini Kita Pernah",Long.valueOf(50000)));
    }

    public List<Book> listBooks() {
        return books;
    }

    public Book getBook(Long id){
        if(id<1){
            return null;
        } else {
            List<Book> result = books.stream()
                    .filter(b -> Objects.equals(b.getId(), id))
                    .collect(Collectors.toList());
            return result.get(0);
        }
    }

    public Book addBook(Book newBook){
        idCounter++;
        Book addBook = new Book(idCounter, newBook.getTitle(), newBook.getPrice());
        books.add(addBook);
        return addBook;
    }

    public Book updateBook(Long id, Book updateBook) {
        if (id > 0) {
            Book existingBook = books.get(id.intValue() - 1);
            existingBook.setTitle(updateBook.getTitle());
            existingBook.setPrice(updateBook.getPrice());
            return existingBook;
        } else {
            return null;
        }
    }

    public void deleteBook(Long id) {
        if (id > 0) {
            books.remove(id.intValue()-1);
        }
    }
}
