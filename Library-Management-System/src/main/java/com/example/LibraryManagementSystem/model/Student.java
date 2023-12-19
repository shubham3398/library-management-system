package com.example.LibraryManagementSystem.model;

import com.example.LibraryManagementSystem.Enum.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
//@Title("student-info") -> to change the name in mysql table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int regNo; //primary key

    String name;//@Column("student-name") -> to change the column name

    int age;

    @Column(unique = true,nullable = false)
    String email;

    @Enumerated(EnumType.STRING)
    Gender gender;

    //mappedBy = "name of Student object declared in child class"
    //cascade -> if a operation(CRUD) is take place here then that operation will happen with all his child table too
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    //@JsonIgnore// to avoid bidirectional recursive calls of json
    LibraryCard libraryCard;
}
