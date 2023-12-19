package com.example.LibraryManagementSystem.DTO.responseDTO;

import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class IssueBookResponse {
    String transactionNumber;

    Date transactionTime;

    TransactionStatus transactionStatus;

    String bookName;

    String authorName;

    String studentName;

    String libraryCardNumber;
}
