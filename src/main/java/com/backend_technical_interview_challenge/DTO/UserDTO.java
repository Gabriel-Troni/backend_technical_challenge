package com.backend_technical_interview_challenge.DTO;

import com.backend_technical_interview_challenge.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(
    Long id,
    String firstName,
    String lastName,
    String document,
    String email,
    String password,
    BigDecimal balance,
    UserType userType
){}
