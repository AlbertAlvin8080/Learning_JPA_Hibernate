package br.com.albert.tests.student_test;

import br.com.albert.model.entity.DAOClasses.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class StudentTest01 {
    public static void main(String[] args) {
        Student s = new Student("Klaus", "Poppe", "klaus@gmail.com", 49);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
