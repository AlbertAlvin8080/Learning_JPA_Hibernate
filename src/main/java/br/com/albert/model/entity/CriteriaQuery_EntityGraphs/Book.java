package br.com.albert.model.entity.CriteriaQuery_EntityGraphs;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Book_Author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authorList;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Book_BookStore",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "bookStore_id")
    )
    private Set<BookStore> bookStoreList;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
        this.authorList = new HashSet<>();
        this.bookStoreList = new HashSet<>();
    }

    public Book(String name, List<Author> authorList, List<BookStore> bookStoreList) {
        this.name = name;
        this.authorList = new HashSet<>();
        this.authorList.addAll(authorList);
        this.bookStoreList = new HashSet<>();
        this.bookStoreList.addAll(bookStoreList);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(Set<Author> authorList) {
        this.authorList = authorList;
    }

    public Set<BookStore> getBookStoreList() {
        return bookStoreList;
    }

    public void setBookStoreList(Set<BookStore> bookStoreList) {
        this.bookStoreList = bookStoreList;
    }
}
