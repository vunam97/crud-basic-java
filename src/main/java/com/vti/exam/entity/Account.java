package com.vti.exam.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Table(name = "Account")
@Getter
@Setter
public class Account {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", length = 50, nullable = false, unique = true, updatable = false)
    private String username;

    @Column(name = "password", length = 800, nullable = false)
    private String password;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Formula(value = "concat(first_name, ' ', last_name)")
    private String fullName;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.EMPLOYEE;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public enum Role {
        ADMIN, EMPLOYEE, MANAGER
    }
}
