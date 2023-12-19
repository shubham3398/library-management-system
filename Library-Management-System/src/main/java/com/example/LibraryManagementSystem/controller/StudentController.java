package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.DTO.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.DTO.responseDTO.ViewStudent;
import com.example.LibraryManagementSystem.service.StudentService;
import com.example.LibraryManagementSystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest){
        StudentResponse response = studentService.addStudent(studentRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
        ViewStudent student = studentService.getStudent(regNo);
        if(student != null) {
            return new ResponseEntity(student, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Invalid Id!!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") int regNo){
        String response = studentService.deleteStudent(regNo);
        if(response != null) {
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity("Invalid Id!!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update-age")
    public ResponseEntity updateAge(@RequestParam("id") int regNo, @RequestParam("age") int newAge){
        String response = studentService.updateAge(regNo, newAge);
        if(response != null) return new ResponseEntity(response, HttpStatus.CREATED);
        return new ResponseEntity("Invalid Id!!", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/get-all-student")
    public ResponseEntity getAllStudent(){
        List<String> studentsName = studentService.getAllStudent();
        return new ResponseEntity(studentsName, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-all-male-student")
    public ResponseEntity getAllMaleStudents(){
        List<String> studentsName = studentService.getAllMaleStudents();
        return new ResponseEntity(studentsName, HttpStatus.ACCEPTED);
    }
}

