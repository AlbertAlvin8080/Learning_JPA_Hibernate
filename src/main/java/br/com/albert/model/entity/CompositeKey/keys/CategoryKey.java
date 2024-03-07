package br.com.albert.model.entity.CompositeKey.keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategoryKey implements Serializable {
    private String code;
    private Integer serialNumber;

    public CategoryKey(String code, Integer serialNumber) {
        this.code = code;
        this.serialNumber = serialNumber;
    }

    public CategoryKey() {}

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
        CategoryKey that = (CategoryKey) o;
        return Objects.equals(code, that.code) && Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, serialNumber);
    }
}
