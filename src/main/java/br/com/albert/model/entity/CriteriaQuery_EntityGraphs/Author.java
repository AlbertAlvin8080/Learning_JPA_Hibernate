package br.com.albert.model.entity.CriteriaQuery_EntityGraphs;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "Author.BookFetchEager",
                attributeNodes = @NamedAttributeNode(value = "bookList")
        ),
        @NamedEntityGraph(
                name = "Author.BookAndBookStoreFetchEager",
                attributeNodes = @NamedAttributeNode(value = "bookList", subgraph = "Book.BookStoreFetchEager"),
                subgraphs = @NamedSubgraph(
                        name = "Book.BookStoreFetchEager",
                        attributeNodes = @NamedAttributeNode(value = "bookStoreList")
                )
        )
})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "authorList")
    private Set<Book> bookList;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
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

    public Set<Book> getBookList() {
        return bookList;
    }

    public void setBookList(Set<Book> bookList) {
        this.bookList = bookList;
    }
}
