package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.CompositeKey.Category;
import br.com.albert.model.entity.CompositeKey.Category2;
import br.com.albert.model.entity.CompositeKey.keys.CategoryKey;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;

public class HibernateFunctionTest04 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            CategoryKey ck = new CategoryKey("UIajaks==ndsaf#s", 76);
            Category2 c = new Category2();
            c.setCategoryKey(ck);
            c.setName("RTX-5090");

            em.persist(c);
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
