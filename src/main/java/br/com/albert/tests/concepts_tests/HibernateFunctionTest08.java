package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.ManyToMany.UGroup;
import br.com.albert.model.entity.ManyToMany.Users;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class HibernateFunctionTest08 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            var user1 = new Users();
            var user2 = new Users();
            var ugroup1 = new UGroup(); ugroup1.setDescription("desc 1");
            var ugroup2 = new UGroup(); ugroup2.setDescription("desc 2");

            user1.setUgroups(List.of(ugroup1));
            user2.setUgroups(List.of(ugroup1, ugroup2));
            ugroup1.setUsers(List.of(user1, user2));
            ugroup2.setUsers((List.of(user2)));

            em.persist(user1);
            em.persist(user2);
            em.persist(ugroup1);
            em.persist(ugroup2);

            em.flush();

            TypedQuery<UGroup> query =
                    em.createQuery("select u from UGroup u where u.description = 'desc 2'", UGroup.class);
            System.out.println(query.getResultList());

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
