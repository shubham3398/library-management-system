package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.DTO.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.DTO.responseDTO.ViewStudent;
import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.model.LibraryCard;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentResponse addStudent(StudentRequest studentRequest) {
        //convert request DTO to model student object -> use transformer
        Student student = StudentTransformer.StudentRequestToStudent(studentRequest);

        //now allocate the library card to this student
        LibraryCard libraryCard = LibraryCard.builder()
                .cardNo(String.valueOf(UUID.randomUUID()))
                .cardStatus(CardStatus.ACTIVE)
                .student(student)
                .build();

        student.setLibraryCard(libraryCard);

        //save into database
        Student savedStudent = studentRepository.save(student);

        //to return, convert the model to responseDto -> use transformer
        StudentResponse response = StudentTransformer.StudentToStudentResponse(savedStudent);

        return response;
    }

    public ViewStudent getStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();

            ViewStudent viewStudent = StudentTransformer.StudentToViewStudentResponse(student);
            return viewStudent;
        }
        return null;
    }

    public String deleteStudent(int regNo) {
        Optional<Student> student = studentRepository.findById(regNo);
        if(student.isPresent()){
            studentRepository.delete(student.get());
            return "Student deleted successfully!!";
        }
        return "null";
    }

    public String updateAge(int regNo, int newAge) {
        Optional<Student> optionalStudent = studentRepository.findById(regNo);
        if (optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            student.setAge(newAge);
            studentRepository.save(student);
            return "Age updated successfully!!";
        }
        return "null";
    }

    public List<String> getAllStudent() {
        List<String> names = new ArrayList<>();
        List<Student> allStudent = studentRepository.findAll();
        for(Student student : allStudent){
            names.add(student.getName());
        }
        return names;
    }

    public List<String> getAllMaleStudents() {
        //isn't it good if we have findByGender method hence just implement this method student Repository interface
        //and implementation will handle by hibernate
        List<Student> students = studentRepository.findByGender(Gender.Male);

        List<String> studentsName = new ArrayList<>();

        for (Student student: students) {
            studentsName.add(student.getName());
        }
        return studentsName;
    }
}
