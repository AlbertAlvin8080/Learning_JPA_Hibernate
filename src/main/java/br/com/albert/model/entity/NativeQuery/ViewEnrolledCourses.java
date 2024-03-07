package br.com.albert.model.entity.NativeQuery;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "view_enrolled_courses")
public class ViewEnrolledCourses {
    @Id
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "ViewEnrolledCourses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
