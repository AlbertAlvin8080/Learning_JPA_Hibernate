package br.com.albert.persistence;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class CustomPersistenceUnitInfo implements PersistenceUnitInfo {
    @Override
    public String getPersistenceUnitName() {
        return "meuPU";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.RESOURCE_LOCAL;
    }

    @Override
    public DataSource getJtaDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/db_hibernate");
        dataSource.setUsername("albert");
        dataSource.setPassword("1234");
        return dataSource;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }

    @Override
    public List<String> getMappingFileNames() {
        return null;
    }

    @Override
    public List<URL> getJarFileUrls() {
        return null;
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        return List.of(
//                "br.com.albert.model.entity.DAOClasses.Professor", "br.com.albert.model.entity.DAOClasses.Student",
//                "br.com.albert.model.entity.GenericGenerator.Product", "br.com.albert.model.entity.CompositeKey.Category", "br.com.albert.model.entity.CompositeKey.Category2",
//                "br.com.albert.model.entity.OneToOne.Person", "br.com.albert.model.entity.OneToOne.Passport",
//                "br.com.albert.model.entity.SecondaryTable.User",
//                "br.com.albert.model.entity.ManyToOne_OneToMany.Post", "br.com.albert.model.entity.ManyToOne_OneToMany.Comment",
//                "br.com.albert.model.entity.ManyToMany.UGroup", "br.com.albert.model.entity.ManyToMany.Users",
//                "br.com.albert.model.entity.Inheritance.Product", "br.com.albert.model.entity.Inheritance.Book", "br.com.albert.model.entity.Inheritance.ElectronicDevice",
//                "br.com.albert.model.entity.Enumerated.Person",
//                "br.com.albert.model.entity.JPQL.Student", "br.com.albert.model.entity.JPQL.Course", "br.com.albert.model.entity.JPQL.Enrollment", "br.com.albert.model.entity.NativeQuery.ViewEnrolledCourses",
                "br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Author", "br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Book", "br.com.albert.model.entity.CriteriaQuery_EntityGraphs.BookStore"
        );
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer classTransformer) {

    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
