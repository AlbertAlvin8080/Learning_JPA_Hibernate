package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Author;
import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Book;
import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.BookStore;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateTest22EntityGraph03 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            EntityGraph<?> graph = em.getEntityGraph("Author.BookAndBookStoreFetchEager");
//            EntityGraph<?> graph = em.createEntityGraph(Author.class);
//            Subgraph<Book> bookSubgraph = graph.addSubgraph("bookList", Book.class);
//            bookSubgraph.addAttributeNodes("bookStoreList");

            TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
            query.setHint("jakarta.persistence.loadgraph", graph)
                    .getResultList()
//                    .forEach(a -> System.out.println(a + " " + a.getBookList()));
                    .forEach(a -> {
                        System.out.println(a);
                        System.out.println(a.getBookList());
                        List<BookStore> collect = a.getBookList()
                                .stream()
                                .map(Book::getBookStoreList)
                                .flatMap(bookStores -> bookStores.stream())
                                .collect(Collectors.toList());
                        System.out.println(collect);
                        System.out.println("------------------------------------");
                    });

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
