package net.javaguides.springbootbackend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity  // to make the class as a JPA Entity
@Table(name = "employees")  // this annotation specifies the table in the database with which this entity is mapped and also to make the class as a jpa entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;


}
