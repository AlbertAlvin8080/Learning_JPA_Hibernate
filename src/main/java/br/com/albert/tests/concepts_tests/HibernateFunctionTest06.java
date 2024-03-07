package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.SecondaryTable.User;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;

public class HibernateFunctionTest06 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            var user = new User();
            user.setName("Kenjaku");
            user.setDescription("Descricao de usuario generico");
            em.persist(user);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
