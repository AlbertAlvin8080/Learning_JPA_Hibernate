package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Author;
import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Book;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;
import jakarta.persistence.TypedQuery;

import java.util.stream.Collectors;

public class HibernateTest21EntityGraph02 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();
            EntityGraph<Author> graph = em.createEntityGraph(Author.class);
            Subgraph<Object> subgraph = graph.addSubgraph("bookList");
            subgraph.addAttributeNodes("bookStoreList");

            TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
            query.setHint("jakarta.persistence.loadgraph", graph);
            query.getResultList().forEach(a -> {
                System.out.println(a);
                System.out.println(a.getBookList());
                a.getBookList()
                        .stream()
                        .map(Book::getBookStoreList)
                        .forEach(bsl -> System.out.printf("%s ", bsl));
                System.out.println("\n-----------------------------");
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
