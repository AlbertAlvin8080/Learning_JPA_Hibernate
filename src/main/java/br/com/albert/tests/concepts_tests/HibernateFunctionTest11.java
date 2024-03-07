package br.com.albert.tests.concepts_tests;

import br.com.albert.model.dto.StudentEnrollmentCount;
import br.com.albert.model.entity.JPQL.Course;
import br.com.albert.model.entity.JPQL.Student;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class HibernateFunctionTest11 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            // bidirectional relationship
//            String jpql = "select s from Student s inner join s.enrollments e";
//            TypedQuery<Student> query = em.createQuery(jpql, Student.class);

//            String jpql = "select c from Course c inner join c.enrollments e";
//            TypedQuery<Course> query = em.createQuery(jpql, Course.class);

//            String jpql = """
//                    select
//                    new br.com.albert.model.dto.StudentEnrollmentCount(
//                        s, (select count(e) from Enrollment e where e.student.id = s.id)
//                    )
//                    from Student s
//                    """;
//            TypedQuery<StudentEnrollmentCount> query = em.createQuery(jpql, StudentEnrollmentCount.class);

            String jpql = """
                    select new br.com.albert.model.dto.StudentEnrollmentCount(s, count(e.student))
                    from Student s
                    left join Enrollment e
                    on s.id = e.student.id
                    group by s.id
                    """;
            TypedQuery<StudentEnrollmentCount> query = em.createQuery(jpql, StudentEnrollmentCount.class);
            query.getResultList().forEach(o -> System.out.println(o));

            // unidirectional relationship
//            String jpql = """
//                    select s, e from Student s left join Enrollment e on s.id = e.student.id order by e.id
//                    """;
//            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
//            query.getResultList().forEach(o -> System.out.println(o[0] + " <-> " + o[1]));

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
