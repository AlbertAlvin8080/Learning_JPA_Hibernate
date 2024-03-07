package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Book;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

public class HibernateTest19CriteriaQuerySubQueries02 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

//          JPQL Query
//          select b, count(bs) from br.com.albert.model.entity.CriteriaQuery.Book b left join b.bookStoreList bs group by b.id
//          The CriteriaQuery below uses a different approach to achieve the same result

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Tuple> mainQuery = builder.createTupleQuery();
            Root<Book> bookRoot = mainQuery.from(Book.class);

            Subquery<Long> subquery = mainQuery.subquery(Long.class);
            Root<Book> correlate = subquery.correlate(bookRoot);
            Join<Object, Object> bookStoreListJoin = correlate.join("bookStoreList", JoinType.LEFT);
            subquery.select(builder.count(bookStoreListJoin.get("id"))); // count(bs.id)

            mainQuery.multiselect(bookRoot, subquery);

            TypedQuery<Tuple> query = em.createQuery(mainQuery);
            query.getResultList().forEach(t -> {
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
