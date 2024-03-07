package br.com.albert.model.entity.OneToOne;

import jakarta.persistence.*;

@Entity
public class Passport {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String code;
//    @OneToOne(mappedBy = "passport")
//    private Person person;

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", code='" + code + '\'' +
//                ", personName=" + person.getName() +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    public Person getPerson() {
//        return person;
//    }
//
//    public void setPerson(Person person) {
//        this.person = person;
//    }
}
