package com.example.LibraryManagementSystem.model;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String cardNo;

    @Enumerated(EnumType.STRING)
    CardStatus cardStatus;

    @CreationTimestamp
    Date issueDate;

    @OneToOne
    @JoinColumn//by default foreign key will be primary key of student table
    Student student;

    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    List<Transaction> transactions = new ArrayList<>();
}
