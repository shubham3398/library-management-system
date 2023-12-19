package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody Book book){
       try {
           String response = bookService.addBook(book);//this can throw exception
           return new ResponseEntity(response, HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }
}
