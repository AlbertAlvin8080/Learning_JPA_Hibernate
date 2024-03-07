package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.JPQL.Course;
import br.com.albert.model.entity.NativeQuery.ViewEnrolledCourses;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;

public class HibernateFunctionTest13 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            TypedQuery<ViewEnrolledCourses> query = em.createQuery("select v from ViewEnrolledCourses v", ViewEnrolledCourses.class);
            query.getResultList().forEach(System.out::println);

            StoredProcedureQuery procedureQuery = em.createStoredProcedureQuery("getCoursesAboveId", Course.class)
                    .registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN)
                    .setParameter("in_id", 2);
            procedureQuery.getResultList().forEach(System.out::println);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
