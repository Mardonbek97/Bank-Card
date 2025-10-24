package com.example.bankrest.controller;

import com.example.bankrest.dto.TransferRequest;
import com.example.bankrest.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> makeTransfer(@Valid @RequestBody TransferRequest request) {
        String result = transferService.transfer(request);
        return ResponseEntity.ok(result);
    }
}
