package com.example.LibraryManagementSystem.DTO.responseDTO;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ViewStudent {
    String name;
    int age;
    String email;
    Gender gender;

    String libraryCardNo;
    CardStatus cardStatus;
    Date issueDate;
}
