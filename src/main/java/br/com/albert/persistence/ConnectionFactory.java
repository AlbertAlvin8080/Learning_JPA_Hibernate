package br.com.albert.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class ConnectionFactory {
//    private static final EntityManagerFactory emt = Persistence.createEntityManagerFactory("meuPU");
    private static final Map<String, Object> map = new HashMap<>();
    static {
        map.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.format_sql", "true");
        map.put("hibernate.hbm2ddl.auto", "none"); // update, create, none
    }
    private static final EntityManagerFactory emt = new HibernatePersistenceProvider()
        .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), map);

    public static EntityManager getConnection() {
        return emt.createEntityManager();
    }
}
