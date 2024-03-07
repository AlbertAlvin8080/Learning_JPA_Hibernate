package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Author;
import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Book;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

public class HibernateTest17CriteriaQuerySubQueries {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

//          JPQL Query
//          select a, count(b) from Author a left join a.bookList b group by a.id
//          Abstraction (the IN syntax is wrong. Beware)
//          select a, (select count(b) from Book b where b.id IN a.bookList) from Author a

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Tuple> mainQuery = builder.createTupleQuery();
            Root<Author> authorRoot = mainQuery.from(Author.class);

            Subquery<Long> subquery = mainQuery.subquery(Long.class);
            Root<Author> correlate = subquery.correlate(authorRoot); // replaces the FROM
            Join<Author, Book> authorBookJoin = correlate.join("bookList", JoinType.LEFT);
            subquery.select(builder.count(authorBookJoin));

            mainQuery.multiselect(authorRoot, subquery);
//                    .where(builder.ge(subquery, 0l));

            TypedQuery<Tuple> query = em.createQuery(mainQuery);
            query.getResultList().forEach(t -> {
//                System.out.println(t.get(0));
                System.out.println(t.get(0) + " " + t.get(1));
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
