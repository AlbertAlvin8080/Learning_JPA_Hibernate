package br.com.albert.model.entity.JPQL;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "getAllStudents", query = """
                select s from Student s
                """),
        @NamedQuery(name = "getDTOStudentEnrollmentCount", query = """
                select new br.com.albert.model.dto.StudentEnrollmentCount(s, (count(e)))
                from Student s left join Enrollment e on e.student.id = s.id
                group by s.id 
                having count(e) > 2
                """),
        @NamedQuery(name = "getEnrolledCourses", query = """
                select c from Course c inner join Enrollment e on e.course.id = c.id
                """)
})
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
