package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.JPQL.Enrollment;
import br.com.albert.model.entity.JPQL.Student;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

public class HibernateTest15CriteriaQueryJoins {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            // ESSE É UM EXEMPLO DE N + 1 QUERY (acidental)

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Tuple> cq = builder.createTupleQuery();

            Root<Enrollment> enrollmentRoot = cq.from(Enrollment.class);
            Join<Enrollment, Student> studentJoin = enrollmentRoot.join("student", JoinType.RIGHT);
//            Join<Object, Object> courseJoin = enrollmentRoot.join("course", JoinType.RIGHT);

            cq.multiselect(
                    enrollmentRoot
                    ,studentJoin
//                    ,courseJoin
                    )
                    .where(builder.like(studentJoin.get("name"), "%o%"))
                    .orderBy(builder.asc(enrollmentRoot.get("id")));

            TypedQuery<Tuple> query = em.createQuery(cq);
            query.getResultList().forEach(t -> System.out.println(
                    t.get(0)
                    + "  " + t.get(1)
//                    + "  " + t.get(2)
            ));

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
