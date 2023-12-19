package com.example.LibraryManagementSystem.transformer;


import com.example.LibraryManagementSystem.DTO.requestDTO.AuthorRequest;
import com.example.LibraryManagementSystem.model.Author;

public class AuthorTransformer {
    public static Author AuthorRequestToAuthor(AuthorRequest authorRequest){
        return Author.builder()
                .name(authorRequest.getName())
                .age(authorRequest.getAge())
                .email(authorRequest.getEmail())
                .build();
    }
}
