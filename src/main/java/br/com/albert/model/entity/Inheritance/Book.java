package br.com.albert.model.entity.Inheritance;

import jakarta.persistence.Entity;

@Entity
public class Book extends Product {
    private String author;

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
