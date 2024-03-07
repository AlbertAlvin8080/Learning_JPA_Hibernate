package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Author;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class HibernateTest20EntityGraph {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();
            EntityGraph<Author> graph = em.createEntityGraph(Author.class);
            // significa: faça o FetchType.EAGER desse atributo (seja de relação ou não) [value = "bookList"]
            graph.addAttributeNodes("bookList");

            TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
            query.setHint("jakarta.persistence.loadgraph", graph);
            query.getResultList().forEach(a -> {
                System.out.println(a + " " + a.getBookList());
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
