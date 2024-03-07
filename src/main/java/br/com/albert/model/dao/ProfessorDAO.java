package br.com.albert.model.dao;

import br.com.albert.persistence.ConnectionFactory;
import br.com.albert.model.entity.DAOClasses.Professor;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProfessorDAO {
    public Professor save(Professor professor) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();
            if (professor.getId() != null) {
                em.merge(professor);
            } else {
                em.persist(professor);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return professor;
    }

    public List<Professor> findAll() {
        EntityManager em = ConnectionFactory.getConnection();
        List<Professor> professors = null;

        try {
            professors = em.createQuery("from Professor c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return professors;
    }
}
