package br.com.albert.tests.student_test;

import br.com.albert.model.entity.DAOClasses.Student;
import br.com.albert.model.dao.StudentDAO;

public class StudentTest03 {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        Student student = dao.findById(3);
        System.out.println(student);
    }
}
