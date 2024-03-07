package br.com.albert.tests.concepts_tests;

import br.com.albert.model.dto.StudentEnrollmentCount;
import br.com.albert.model.entity.JPQL.Course;
import br.com.albert.model.entity.JPQL.Student;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class HibernateFunctionTest12 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

//            TypedQuery<Student> query1 = em.createNamedQuery("getAllStudents", Student.class);
//            query1.getResultList().forEach(System.out::println);

//            TypedQuery<StudentEnrollmentCount> query2 = em.createNamedQuery("getDTOStudentEnrollmentCount", StudentEnrollmentCount.class);
//            query2.getResultList().forEach(o -> System.out.println(o));

            TypedQuery<Course> query3 = em.createNamedQuery("getEnrolledCourses", Course.class);
            query3.getResultList().forEach(o -> System.out.println(o));

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
