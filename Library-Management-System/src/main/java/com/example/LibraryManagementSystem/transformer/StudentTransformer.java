package com.example.LibraryManagementSystem.transformer;

import com.example.LibraryManagementSystem.DTO.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.DTO.responseDTO.ViewStudent;
import com.example.LibraryManagementSystem.model.Student;

public class StudentTransformer {
    public static Student StudentRequestToStudent(StudentRequest studentRequest){
         Student student = Student.builder()
                .name(studentRequest.getName())
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .build();
         return student;
    }

    public static StudentResponse StudentToStudentResponse(Student student){
        return StudentResponse.builder()
                .name(student.getName())
                .age(student.getAge())
                .message("You have been saved !!")
                .issuedLibraryCardNO(student.getLibraryCard().getCardNo())
                .build();
    }

    public static ViewStudent StudentToViewStudentResponse(Student student){
        return ViewStudent.builder()
                .name(student.getName())
                .age(student.getAge())
                .email(student.getEmail())
                .gender(student.getGender())
                .libraryCardNo(student.getLibraryCard().getCardNo())
                .cardStatus(student.getLibraryCard().getCardStatus())
                .issueDate(student.getLibraryCard().getIssueDate())
                .build();
    }
}
