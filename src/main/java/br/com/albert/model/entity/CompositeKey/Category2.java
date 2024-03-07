package br.com.albert.model.entity.CompositeKey;

import br.com.albert.model.entity.CompositeKey.keys.CategoryKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Category2 {
    @EmbeddedId
    private CategoryKey categoryKey;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryKey getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(CategoryKey categoryKey) {
        this.categoryKey = categoryKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category2 category2 = (Category2) o;
        return Objects.equals(categoryKey, category2.categoryKey) && Objects.equals(name, category2.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryKey, name);
    }
}