package br.com.albert.tests.professor_test;

import br.com.albert.model.dao.ProfessorDAO;
import br.com.albert.model.entity.DAOClasses.Professor;

public class ProfessorTest02 {
    public static void main(String[] args) {
        ProfessorDAO dao = new ProfessorDAO();
        var p = new Professor();
        p.setId(2);
        p.setName("Chise");
        p.setSurname("Hatori");
        p.setSubject("Sorcery");
        dao.save(p);
        for(var professor : dao.findAll()) {
            System.out.println(professor);
        }
//        Breaking bound between student and professor
//        StudentDAO sdao = new StudentDAO();
//        Student student = sdao.findById(8);
//        student.setProfessor(null);
//        sdao.save(student);
    }
}
