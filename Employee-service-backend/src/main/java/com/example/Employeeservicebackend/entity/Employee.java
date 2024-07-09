package com.example.Employeeservicebackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_Name",nullable = false)
    private String firstName;

    @Column(name = "last_Name",nullable = false)
    private String lastName;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "age",nullable = false)
    private Integer age;

    @Column(name = "date_Of_birth",nullable = false)
    private Date dateOfBirth;

    @Column(name = "gender",nullable = false)
    private String gender;

}
