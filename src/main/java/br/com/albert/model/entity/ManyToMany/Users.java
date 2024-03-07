package br.com.albert.model.entity.ManyToMany;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany
    @JoinTable(
            name = "users_ugroup",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "ugroup_id")
    )
    private List<UGroup> ugroups;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<UGroup> getUgroups() {
        return ugroups;
    }

    public void setUgroups(List<UGroup> ugroups) {
        this.ugroups = ugroups;
    }
}
