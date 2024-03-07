package br.com.albert.model.dao;

import br.com.albert.persistence.ConnectionFactory;
import br.com.albert.model.entity.DAOClasses.Student;
import jakarta.persistence.EntityManager;

import java.util.List;

public class StudentDAO {
    public Student save(Student student) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return student;
    }

    public Student update(Student student) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return student;
    }

    public Student findById(int id) {
        EntityManager em = ConnectionFactory.getConnection();
        Student student = null;

        try {
            student = em.find(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return student;
    }

    public Student remove(int id) {
        EntityManager em = ConnectionFactory.getConnection();
        Student student = null;

        try {
            student = em.find(Student.class, id);
            if(student != null) {
                em.getTransaction().begin();
                em.remove(student);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

        return student;
    }

    public List<Student> findAll() {
        EntityManager em = ConnectionFactory.getConnection();
        List<Student> students = null;

        try {
            students = em.createQuery("from Student c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return students;
    }
}
