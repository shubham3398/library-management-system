package com.example.LibraryManagementSystem.DTO.requestDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthorRequest {
    String name;
    int age;
    String email;
}
