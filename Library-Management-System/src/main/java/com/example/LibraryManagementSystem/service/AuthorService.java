package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.DTO.requestDTO.AuthorRequest;
import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.transformer.AuthorTransformer;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;
    public String addAuthor(AuthorRequest authorRequest) {
        //convert the request into author
        Author author = AuthorTransformer.AuthorRequestToAuthor(authorRequest);
        Author savedAuthor = authorRepository.save(author);
        return "Author Added Successfully !!";
    }

    public String deleteAuthor(int authorId) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        try{
            //check author present or not
            if(optionalAuthor.isEmpty()){
                throw new AuthorNotFoundException("Author is not found! Please Enter a Valid Author id!!");
            }

            Author author = optionalAuthor.get();
            //delete all the books of particular author
            //1> get all the books
            List<Book> books = bookService.getAllBooksOfAuthor(author);
            //delete the all books
            for(Book book : books){
                bookRepository.delete(book);
            }

            //delete the author
            authorRepository.delete(author);

            return "Author is Deleted Succesfully";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    public String changeAuthorEmail(int authorId, String newEmail) {
        //first get the author
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if(optionalAuthor.isEmpty()){
            throw new AuthorNotFoundException("Enter Invalid Author id!!");
        }

        Author author = optionalAuthor.get();
        author.setEmail(newEmail);

        //save in database
        authorRepository.save(author);
        return "Email has been changed Succesfully!!";
    }
}
