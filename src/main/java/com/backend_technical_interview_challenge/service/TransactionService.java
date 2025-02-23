package com.backend_technical_interview_challenge.service;

import com.backend_technical_interview_challenge.DTO.NotificationDTO;
import com.backend_technical_interview_challenge.DTO.TransactionDTO;
import com.backend_technical_interview_challenge.domain.transaction.Transaction;
import com.backend_technical_interview_challenge.domain.user.User;
import com.backend_technical_interview_challenge.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public void saveTransaction(TransactionDTO data) throws Exception {
        // Instancia os usuários envolvidos
        User sender = userService.findUserById(data.senderId());
        User receiver = userService.findUserById(data.receiverId());

        // método para validar se o remetente tem saldo e se pode enviar dinheiro
        userService.validateTransaction(sender, data.value());

        // Valida em um serviço externo
        if (!authorizeTransaction()){
            throw new Exception("Transação não autorizada!");
        };

        // Instancia a nova transação
        Transaction transaction = new Transaction();
        transaction.setAmount(data.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        // Atualiza o saldo dos envolvidos
        sender.setBalance(sender.getBalance().subtract(data.value()));
        receiver.setBalance(receiver.getBalance().add(data.value()));

        // Salva no banco de dados
        this.transactionRepository.save(transaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        // Notificar
        String senderMessage =
                "Você enviou "+data.value()+" para "+receiver.getFirstName()+" "+receiver.getLastName();
        String receiverMessage =
                "Você recebeu "+data.value()+" de "+sender.getFirstName()+" "+sender.getLastName();
        NotificationDTO senderNotification = new NotificationDTO(sender.getEmail(), senderMessage);
        NotificationDTO receiverNotification = new NotificationDTO(receiver.getEmail(), receiverMessage);

        //notificationService.sendNotification(senderNotification);
        //notificationService.sendNotification(receiverNotification);

    }

    public boolean authorizeTransaction() {
        ResponseEntity<Map> response = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("data")) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
                return (Boolean) data.get("authorization");
            }
        }
        return false;
    }
}
