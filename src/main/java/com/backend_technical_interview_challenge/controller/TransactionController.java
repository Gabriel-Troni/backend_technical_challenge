package com.backend_technical_interview_challenge.controller;

import com.backend_technical_interview_challenge.DTO.TransactionDTO;
import com.backend_technical_interview_challenge.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDTO data) throws Exception {
        transactionService.saveTransaction(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
