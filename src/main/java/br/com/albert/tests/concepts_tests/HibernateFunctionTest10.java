package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.Enumerated.Person;
import br.com.albert.model.entity.Enumerated.enums.Gender;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;

public class HibernateFunctionTest10 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            /*var person1 = new Person();
            person1.setGender(Gender.MALE);
            person1.setName("Gojo");
            person1.setIncome(BigDecimal.valueOf(78.79));

            var person2 = new Person();
            person2.setGender(Gender.OTHER);
            person2.setName("Summa Blasphemia");
            person2.setIncome(BigDecimal.valueOf(38.39));

            var person3 = new Person();
            person3.setGender(Gender.FEMALE);
            person3.setName("Nakano Miku");
            person3.setIncome(BigDecimal.valueOf(98.99));

            em.persist(person1);
            em.persist(person2);
            em.persist(person3);*/

            String jpql = "select sum(p.income), p.gender from Person p group by p.gender";
//            TypedQuery<Person> query = em.createQuery(jpql, Person.class);
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.getResultList().forEach(obj -> System.out.println(obj[0] + " " + obj[1]));
//            System.out.println(query.getSingleResult());

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
