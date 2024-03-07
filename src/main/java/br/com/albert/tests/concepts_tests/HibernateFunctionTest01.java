package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.DAOClasses.Student;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;

public class HibernateFunctionTest01 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

//            Student s = em.getReference(Student.class, 2);
            Student s = em.find(Student.class, 2);
//            s.setProfessor(null);
//            s.setProfessor(new Professor(2));
//            em.refresh(s);
//            var p = new Professor("Klaus", "Poppe", "Soviet Learning");
//            em.persist(p);
            em.detach(s);
            s.setAge(9999);
            System.out.println(s);
//            s.setProfessor(new Professor(3));
//            em.remove(em.find(Professor.class, 4));

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
