package br.com.albert.model.dto;

import br.com.albert.model.entity.JPQL.Student;

public record StudentEnrollmentCount(Student student, Long count) {
    @Override
    public String toString() {
        return "StudentEnrollmentCount{" +
                "student=" + student +
                ", count=" + count +
                '}';
    }
}
