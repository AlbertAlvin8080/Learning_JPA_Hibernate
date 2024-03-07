package br.com.albert.model.dao;

import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Author;
import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Book;
import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.BookStore;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class BookDAO {
    public List<Book> findAll() {
        EntityManager em = ConnectionFactory.getConnection();
        List<Book> bookList = null;

        try {
            em.getTransaction().begin();

            TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
            bookList = query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return bookList;
    }

    public List<Book> findAllV2() {
        EntityManager em = ConnectionFactory.getConnection();
        List<Book> bookList = null;

        try {
            em.getTransaction().begin();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Book> mainQuery = builder.createQuery(Book.class);
            Root<Book> bookRoot = mainQuery.from(Book.class);
            mainQuery.select(bookRoot);

            EntityGraph<Book> graph = em.createEntityGraph(Book.class);
            graph.addAttributeNodes("authorList");
            graph.addAttributeNodes("bookStoreList");

            TypedQuery<Book> query = em.createQuery(mainQuery).setHint("jakarta.persistence.loadgraph", graph);
            bookList = query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return bookList;
    }

    public Book insertBook(Book book) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            em.persist(book);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return book;
    }

    public Book insertBook(Book book, String authorName, String storeName) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            {
                String authorQuery = """
                        select a from Author a where a.name = :name
                        """;
                List<Author> list = em.createQuery(authorQuery, Author.class)
                        .setParameter("name", authorName)
                        .getResultList();
                if (list.isEmpty())
                    book.getAuthorList().add(new Author(authorName));
                else
                    book.getAuthorList().add(list.get(0));
            }

            {
                String storeQuery = """
                        select bs from BookStore bs where bs.name = :name
                        """;
                List<BookStore> list = em.createQuery(storeQuery, BookStore.class)
                        .setParameter("name", storeName)
                        .getResultList();

                if (list.isEmpty())
                    book.getBookStoreList().add(new BookStore(storeName));
                else
                    book.getBookStoreList().add(list.get(0));
            }

            em.persist(book);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return book;
    }

    public Book updateBook(String oldName, String newName) {
        EntityManager em = ConnectionFactory.getConnection();
        Book book = null;

        try {
            em.getTransaction().begin();

            TypedQuery<Book> query = em.createQuery("select b from Book b where b.name = :name", Book.class);
            query.setParameter("name", oldName);
            book = query.getSingleResult();
            book.setName(newName);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return book;
    }

    public Book deleteBook(String name) {
        EntityManager em = ConnectionFactory.getConnection();
        Book book = null;

        try {
            em.getTransaction().begin();

            TypedQuery<Book> query = em.createQuery("select b from Book b where b.name = :name", Book.class);
            query.setParameter("name", name);
            book = query.getSingleResult();
            em.remove(book);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return book;
    }
}
