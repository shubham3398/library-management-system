package com.example.LibraryManagementSystem.DTO.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentResponse {

    String name;

    int age;

    String message;

    String issuedLibraryCardNO;
}
