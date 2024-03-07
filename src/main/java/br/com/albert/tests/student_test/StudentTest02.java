package br.com.albert.tests.student_test;

import br.com.albert.model.entity.DAOClasses.Student;
import br.com.albert.model.dao.StudentDAO;

public class StudentTest02 {
    public static void main(String[] args) {
        Student s = new Student("Boulos", "Chocolate", "docinho@gmail.com", 1391139);
//      set an id for updating
        s.setId(6);

        StudentDAO sdao = new StudentDAO();
        sdao.save(s);
    }
}
