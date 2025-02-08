package com.backend_technical_interview_challenge.service;

import com.backend_technical_interview_challenge.domain.user.User;
import com.backend_technical_interview_challenge.domain.user.UserType;
import com.backend_technical_interview_challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public User findUserById(Long id) throws Exception{
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário não pode fazer transferências");
        }

        if(amount.compareTo(sender.getBalance()) > 0){
            throw new Exception("Saldo insuficiente");
        }
    }
}
