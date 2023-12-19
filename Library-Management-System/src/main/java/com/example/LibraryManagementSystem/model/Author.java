package com.example.LibraryManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int age;

    @Column(unique = true, nullable = false)
    String email;

    @UpdateTimestamp //-> every time author do any activities this will update the time
    Date lastActivity;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<Book> books = new ArrayList<>();// initially i want my books list empty hence i created object here
}
