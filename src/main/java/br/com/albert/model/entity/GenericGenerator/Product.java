package br.com.albert.model.entity.GenericGenerator;

import br.com.albert.model.generator.UUIDCustomGenerator;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
//@Table(name = "product")
public class Product {
    @GenericGenerator(name = "UUIDGenerator", type = UUIDCustomGenerator.class)
    @GeneratedValue(generator = "UUIDGenerator")
    @Id
    @Column(length = 350)
    private String id;
    private String name;

    public Product() {}

    public Product(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
