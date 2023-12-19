package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.DTO.responseDTO.IssueBookResponse;
import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import com.example.LibraryManagementSystem.exception.BookNotAvailableException;
import com.example.LibraryManagementSystem.exception.StudentNotFoundException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.model.Transaction;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public IssueBookResponse issueBook(int bookId, int studentId) {
        //first check student is valid?
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if(optionalStudent.isEmpty()){
            throw new StudentNotFoundException("Student is not found!");
        }
        Student student = optionalStudent.get();
        //check is valid ?
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()) {
            throw new BookNotAvailableException("Book is not found!!");
        }

        //now both student and books are valid. now check book is available to isseue or not
        Book book = optionalBook.get();
        if(book.isIssued()){
            throw new BookNotAvailableException("Books is not available!!");
        }

        //here all is good now issue the book to student
        //start with transaction
        Transaction transaction = Transaction.builder()
                .transactionNumber(String.valueOf(UUID.randomUUID()))
                .transactionTime(new Date())
                .transactionStatus(TransactionStatus.SUCCESS)
                .book(book)
                .libraryCard(student.getLibraryCard())
                .build();

        //save this transaction to database
        Transaction savedTransaction = transactionRepository.save(transaction);

        //update in book
        book.setIssued(true);
        book.getTransactions().add(transaction);

        //now update in students library card
        student.getLibraryCard().getTransactions().add(transaction);

        //now save both updated student and book in database
        Student savedStudent = studentRepository.save(student);
        Book savedBook = bookRepository.save(book);

        String text = "Hi! " + student.getName() + " The below book has been issued to you\n" +
                book.getTitle() + " \nThis is the transaction number: "+savedTransaction.getTransactionNumber();

        //now email student to notify
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("vishwakarma331998@gmail.com");
        simpleMailMessage.setTo(student.getEmail());
        simpleMailMessage.setSubject("Congrats!! you issued Book");
        simpleMailMessage.setText(text);

        //send
        javaMailSender.send(simpleMailMessage);

        return IssueBookResponse.builder()
                .transactionNumber(transaction.getTransactionNumber())
                .transactionStatus(transaction.getTransactionStatus())
                .bookName(book.getTitle())
                .authorName(book.getAuthor().getName())
                .studentName(student.getName())
                .transactionTime(transaction.getTransactionTime())
                .libraryCardNumber(student.getLibraryCard().getCardNo())
                .build();
    }


    public String returnBook(int bookId, int studentId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(!optionalBook.isEmpty()){
            Book book = optionalBook.get();
            book.setIssued(false);

            bookRepository.save(book);
            return "Book return successfull!!";
        }

        return "invalid book id";
    }
}
