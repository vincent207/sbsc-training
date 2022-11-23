package com.sgi.springtraining.rest.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books", indexes = {
        @Index(name = "idx_book_id", columnList = "id")
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min=5, max=100)
    @Column(name = "title")
    private String title;

    @Column(name = "price")
    @NotNull
    @Min(1000)
    @Max(999999)
    private Long price;

    public Book() {
    }

    public Book(String title, Long price) {
        this.title = title;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", price=" + price + "]";
    }
}
