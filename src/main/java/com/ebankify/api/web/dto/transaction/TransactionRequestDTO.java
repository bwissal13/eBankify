package com.ebankify.api.web.dto.transaction;

import com.ebankify.api.entity.BankAccount;
import com.ebankify.api.entity.Transaction;
import com.ebankify.api.entity.enums.TransactionStatus;
import com.ebankify.api.entity.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record TransactionRequestDTO(
        @NotNull(message = "Transaction type is required")
        TransactionType type,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        Double amount,

        @NotNull(message = "Source account ID is required")
        Long accountFromId,

        @NotNull(message = "Destination account ID is required")
        Long accountToId,

        @NotNull(message = "Transaction status is required")
        TransactionStatus status,

        LocalDate date
) {
    public Transaction toTransaction(BankAccount accountFrom, BankAccount accountTo) {
        return Transaction.builder()
                .type(type != null ? type : TransactionType.CLASSIC)
                .amount(amount)
                .accountFrom(accountFrom)
                .accountTo(accountTo)
                .status(status != null ? status : TransactionStatus.PENDING)
                .date(date != null ? date : LocalDate.now())
                .build();
    }
}
