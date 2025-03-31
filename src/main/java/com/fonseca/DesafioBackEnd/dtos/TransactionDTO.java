package com.fonseca.DesafioBackEnd.dtos;

import com.fonseca.DesafioBackEnd.domain.transaction.Transaction;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal amount, Long sender_id, Long receiver_id) {
    public TransactionDTO(Transaction data){
        this(data.getAmount(), data.getSender().getId(), data.getReceiver().getId());
    }
}
