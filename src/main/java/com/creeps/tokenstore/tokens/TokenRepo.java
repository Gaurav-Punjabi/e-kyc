package com.creeps.tokenstore.tokens;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Iterator;

public interface TokenRepo extends JpaRepository<Token, Long> {
    Iterator<Token> findByConsumer(Long consumer);
    Iterator<Token> findByConsumerAndUser(Long consumer, Long user);
    Token findByToken(String token);
}
