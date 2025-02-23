package com.backend_technical_interview_challenge.DTO;

import java.math.BigDecimal;

public record TransactionDTO (
        BigDecimal value,
        Long senderId,
        Long receiverId
){}