package br.com.albert.model.entity.CompositeKey;

import br.com.albert.model.entity.CompositeKey.keys.CategoryKey;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.util.Objects;

@Entity
@IdClass(CategoryKey.class)
public class Category {
    @Id
    private String code;
    @Id
    private Integer serialNumber;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(code, category.code) && Objects.equals(serialNumber, category.serialNumber) && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, serialNumber, name);
    }
}