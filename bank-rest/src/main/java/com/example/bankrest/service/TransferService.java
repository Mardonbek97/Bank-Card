package com.example.bankrest.service;

import com.example.bankrest.dto.TransferRequest;
import com.example.bankrest.entity.Card;
import com.example.bankrest.entity.Transaction;
import com.example.bankrest.repository.CardRepository;
import com.example.bankrest.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;

    public TransferService(CardRepository cardRepository, TransactionRepository transactionRepository) {
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
    }


    @Transactional
    public String transfer(TransferRequest request) {

        Card debit = cardRepository.findByCardNumber(request.getDebitCard())
                .orElseThrow(() -> new RuntimeException("Карта-отправитель не найдена"));

        Card credit = cardRepository.findByCardNumber(request.getCreditCard())
                .orElseThrow(() -> new RuntimeException("Карта-получатель не найдена"));

        if (debit.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Недостаточно средств");
        }

        debit.setBalance(debit.getBalance().subtract(request.getAmount()));
        credit.setBalance(credit.getBalance().add(request.getAmount()));
        cardRepository.save(debit);
        cardRepository.save(credit);

        Transaction transaction = new Transaction();
        transaction.setDebitCard(debit);
        transaction.setCreditCard(credit);
        transaction.setAmount(request.getAmount());
        transaction.setStatus("SUCCESS");
        transactionRepository.save(transaction);

        return "Перевод успешно выполнен";
    }


}
