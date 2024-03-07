package br.com.albert.tests.concepts_tests;

import br.com.albert.model.entity.OneToOne.Passport;
import br.com.albert.model.entity.OneToOne.Person;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;

public class HibernateFunctionTest05 {
    public static void main(String[] args) {
        EntityManager em = ConnectionFactory.getConnection();

        try {
            em.getTransaction().begin();

            var passport = new Passport(); passport.setCode("fghjkl;lkjhg="); passport.setId(35);
            var person = new Person(); person.setName("Violin"); person.setPassport(passport);
//            passport.setPerson(person);

            // remember, its @OneToOne, so the foreign key is UNIQUE
            em.persist(passport);
            em.persist(person);
            em.flush();
            System.out.println("----------------------------");

            System.out.println(em.find(Person.class, 1));

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
