package com.backend_technical_interview_challenge.service;

import com.backend_technical_interview_challenge.DTO.UserDTO;
import com.backend_technical_interview_challenge.domain.user.User;
import com.backend_technical_interview_challenge.domain.user.UserType;
import com.backend_technical_interview_challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser (UserDTO data) {
        User user = new User(data);
        User newUser = this.userRepository.save(user);
        return new UserDTO(
                newUser.getId(),
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getDocument(),
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getBalance(),
                newUser.getUserType()
        );
    }

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

    public List<UserDTO> getAllUsers() {
        List<User> data = this.userRepository.findAll();
        List<UserDTO> users = new ArrayList<>();
        for(User user : data) {
            users.add(new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDocument(),
                user.getEmail(),
                user.getPassword(),
                user.getBalance(),
                user.getUserType()
            ));
        }
        return users;
    }
}
