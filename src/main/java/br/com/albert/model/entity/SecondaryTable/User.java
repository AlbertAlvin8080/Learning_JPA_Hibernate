package br.com.albert.model.entity.SecondaryTable;

import jakarta.persistence.*;

@Entity
@SecondaryTable(
        name = "user_description",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id")
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(table = "user_description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
