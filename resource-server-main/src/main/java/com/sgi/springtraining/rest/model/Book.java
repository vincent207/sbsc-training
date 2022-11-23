package com.sgi.springtraining.rest.model;

public class Book {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    private Long price;

    public Book(Long id, String title, Long price){
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
