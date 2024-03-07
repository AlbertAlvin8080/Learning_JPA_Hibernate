package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.JPQL.Course;
import br.com.albert.model.entity.JPQL.Enrollment;
import br.com.albert.model.entity.JPQL.Student;
import br.com.albert.model.entity.NativeQuery.ViewEnrolledCourses;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HibernateTest14CriteriaQuery {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Tuple> cq = builder.createTupleQuery();

            Root<Student> studentRoot = cq.from(Student.class);

            cq.multiselect(studentRoot.get("name"), studentRoot.get("id"))
                    .where(builder.ge(studentRoot.get("id"), 1))
                    .orderBy(builder.desc(studentRoot.get("name")));

            TypedQuery<Tuple> query = em.createQuery(cq);
            query.getResultList().forEach(t -> System.out.println(t.get(0) + " " + t.get(1)));

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
