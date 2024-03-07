package br.com.albert.tests.concepts_tests;

import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class HibernateTest16JPQLManyToManyJoins {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

//            TypedQuery<Object[]> query = em.createQuery("select a, b from Book b inner join b.author a", Object[].class);
//            TypedQuery<Object[]> query = em.createQuery("select bs, b, a from BookStore bs join bs.book b left join b.author a order by bs.id", Object[].class);
//            TypedQuery<Object[]> query = em.createQuery("select b, bs, a from Book b left join b.bookStore bs left join b.author a", Object[].class);
//            query.getResultList().forEach(o -> System.out.println(o[0] + " " + o[1]));
//            query.getResultList().forEach(o -> System.out.println(o[0] + " " + o[1] + " " + o[2]));

            // Author + quantidade de livros
//            TypedQuery<Object[]> query = em.createQuery("select a, count(b) from Author a left join a.bookList b group by a.id", Object[].class);
            TypedQuery<Object[]> query = em.createQuery("""
                    select b, count(bs) from br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Book b left join b.bookStoreList bs group by b.id
                    """, Object[].class);
//            TypedQuery<Object[]> query = em.createQuery("select a, b from Author a left join a.bookList b", Object[].class);
            query.getResultList().forEach(o -> {
                for(int i = 0; i < o.length; ++i) {
                    System.out.print(o[i] + " ");
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
