package com.vti.exam.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Department")
@Getter
@Setter
public class Department {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "total_member", nullable = false)
    private int totalMember;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Account> accounts;

    public enum Type {
        DEV, TEST, SCRUM_MASTER, PM
    }
}
