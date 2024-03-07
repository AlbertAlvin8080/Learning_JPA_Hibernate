package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.CompositeKey.Category;
import br.com.albert.model.entity.CompositeKey.keys.CategoryKey;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;

public class HibernateFunctionTest03 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            Category c = new Category();
            c.setCode("dsafDFsaf#1");
            c.setSerialNumber(1);
            c.setName("RTX-1070");

//            em.persist(c);
            System.out.println(
                    em.find(Category.class, new CategoryKey("dsafDFsaf#1", 9))
            );

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
