package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.transformer.AuthorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public String addBook(Book book) {
       int authorid = book.getAuthor().getId();
       Optional<Author> optionalAuthor = authorRepository.findById(authorid);
       //check author registered or not
       if(optionalAuthor.isEmpty()) {
           //not register
           throw new AuthorNotFoundException("Invalid Author Id!!");
       }
        //get the author
       Author author = optionalAuthor.get();
       //set the Author now
        book.setAuthor(author);
        //save the books in list of author
        author.getBooks().add(book);

        //now save both to database
        authorRepository.save(author);
        return "Book added Successfully!!";
    }

    public List<Book> getAllBooksOfAuthor(Author author) {
        List<Book> authorAllBooks = bookRepository.findByAuthor(author);

        return authorAllBooks;
    }
}
