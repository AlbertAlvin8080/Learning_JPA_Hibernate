package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.BookStore;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

public class HibernateTest18CriteriaQueryGroupBy {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Object[]> mainQuery = builder.createQuery(Object[].class);
            Root<BookStore> bookStoreRoot = mainQuery.from(BookStore.class);
            Join<Object, Object> bookJoin = bookStoreRoot.join("bookList", JoinType.LEFT);

            mainQuery.multiselect(bookStoreRoot, builder.count(bookJoin.get("id")))
                    .groupBy(bookStoreRoot.get("id"));

            TypedQuery<Object[]> query = em.createQuery(mainQuery);
            query.getResultList().forEach(o -> {
                for(int i = 0; i < o.length; ++i) {
                    System.out.printf("%s ", o[i]);
                }
                System.out.println();
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
