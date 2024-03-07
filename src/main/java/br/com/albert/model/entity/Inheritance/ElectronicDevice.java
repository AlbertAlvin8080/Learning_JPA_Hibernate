package br.com.albert.model.entity.Inheritance;

import jakarta.persistence.Entity;

@Entity
public class ElectronicDevice extends Product {
    private Integer voltage;

    public Integer getVoltage() {
        return voltage;
    }

    public void setVoltage(Integer voltage) {
        this.voltage = voltage;
    }

    @Override
    public String toString() {
        return "ElectronicDevice{" +
                "voltage=" + voltage +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
