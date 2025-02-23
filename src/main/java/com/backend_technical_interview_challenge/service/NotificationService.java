package com.backend_technical_interview_challenge.service;

import com.backend_technical_interview_challenge.DTO.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    RestTemplate restTemplate;

    public void sendNotification(NotificationDTO data) throws Exception {
        ResponseEntity<String> response = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", data, String.class);

        if(response.getStatusCode() != HttpStatus.OK){
            throw new Exception("Erro ao enviar e-mail");
        }
    }
}
