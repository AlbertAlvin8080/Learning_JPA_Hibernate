package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.GenericGenerator.Product;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;

public class HibernateFunctionTest02 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            em.persist(new Product("Panela de pressão"));

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
