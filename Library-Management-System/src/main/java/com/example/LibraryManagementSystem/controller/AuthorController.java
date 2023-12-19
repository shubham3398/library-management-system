package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.DTO.requestDTO.AuthorRequest;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.service.AuthorService;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody AuthorRequest authorRequest){
        String response = authorService.addAuthor(authorRequest);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAuthor(@RequestParam("id") int authorId){
        String response = authorService.deleteAuthor(authorId);

        return new ResponseEntity (response, HttpStatus.ACCEPTED);
    }
    @PutMapping("/change-author-email/{email}")
    public ResponseEntity changeAuthorEmail(@RequestParam("id") int authorId, @PathVariable("email") String newEmail){
        try{
            String response = authorService.changeAuthorEmail(authorId, newEmail);
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
