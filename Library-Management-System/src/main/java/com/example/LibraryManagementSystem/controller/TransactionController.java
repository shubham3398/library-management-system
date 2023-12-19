package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.DTO.responseDTO.IssueBookResponse;
import com.example.LibraryManagementSystem.model.Transaction;
import com.example.LibraryManagementSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue-book/book-id/{bookId}/student-id/{studentId}")
    public ResponseEntity issueBook(@PathVariable("bookId") int bookId,@PathVariable("studentId") int studentId){
        try{
            IssueBookResponse response = transactionService.issueBook(bookId, studentId);

            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/return/student-id/{s-id}")
    public ResponseEntity returnBook(@RequestParam("b-id") int bookId, @PathVariable("s-id") int studentId){
        String response = transactionService.returnBook(bookId, studentId);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
