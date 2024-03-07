package br.com.albert.tests.professor_test;

import br.com.albert.model.entity.DAOClasses.Professor;
import br.com.albert.model.dao.ProfessorDAO;

public class ProfessorTest01 {
    public static void main(String[] args) {
        Professor p = new Professor();
//        p.setId(1);
        p.setName("Ghislaine");
        p.setSurname("Dedoldia");
        p.setSubject("Combat");

        ProfessorDAO dao = new ProfessorDAO();
        dao.save(p);
    }
}
