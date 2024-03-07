package br.com.albert.model.entity.ManyToMany;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @ManyToMany(mappedBy = "ugroups")
    private List<Users> users;

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

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

    @Override
    public String toString() {
        return "UGroup{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", users=" + users +
                '}';
    }
}
