package com.example.bankrest.repository;

import com.example.bankrest.entity.Card;
import com.example.bankrest.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {

    Page<Card> findByUser(User user, Pageable pageable);

    Optional<Card> findByIdAndUser(UUID id, User user);

    boolean existsByCardNumber(String cardNumber);

    Optional<Card> findByCardNumber(String cardNumber);

    Optional<Card> findById(UUID id);

}
