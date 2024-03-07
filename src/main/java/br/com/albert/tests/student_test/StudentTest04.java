package br.com.albert.tests.student_test;

import br.com.albert.model.entity.DAOClasses.Student;
import br.com.albert.model.dao.StudentDAO;

public class StudentTest04 {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

//        dao.saveOrUpdate(new Student("MC", "Bial", "bial@gmail.cum", 30));

        for(Student student : dao.findAll()) {
            System.out.println(student);
        }
        System.out.println("----------------------------------");

//        System.out.println(dao.remove(5));
    }
}
