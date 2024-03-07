package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.Inheritance.Book;
import br.com.albert.model.entity.Inheritance.ElectronicDevice;
import br.com.albert.model.entity.Inheritance.Product;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class HibernateFunctionTest09 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            var book = new Book();
            book.setName("Vinland Saga");
            book.setAuthor("Thorfin Kenjaku");
            var electronic = new ElectronicDevice();
            electronic.setName("TV");
            electronic.setVoltage(220);

            em.persist(book);
            em.persist(electronic);

            em.flush();
            System.out.println("--------------------------------");
//            TypedQuery<Product> query = em.createQuery("select p from Product p", Product.class);
            TypedQuery<Product> query = em.createQuery("select p from Product p", Product.class);
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
