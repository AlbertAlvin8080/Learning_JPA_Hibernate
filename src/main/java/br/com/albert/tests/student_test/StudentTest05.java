package br.com.albert.tests.student_test;

import br.com.albert.model.entity.DAOClasses.Professor;
import br.com.albert.model.entity.DAOClasses.Student;
import br.com.albert.model.dao.StudentDAO;

public class StudentTest05 {
    public static void main(String[] args) {
        Student s = new Student("Eris", "Greirart", "eris@gmail.com", 20);
        s.setId(3);

        Professor p = new Professor();
        p.setId(1);
        s.setProfessor(p);

        StudentDAO dao = new StudentDAO();
        dao.save(s);

        for(Student student : dao.findAll()) {
            System.out.println(student);
        }

    }
}
