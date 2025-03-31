package com.fonseca.DesafioBackEnd.service;

import com.fonseca.DesafioBackEnd.domain.user.User;
import com.fonseca.DesafioBackEnd.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void notificationSender(String message, User user) throws Exception {

        NotificationDTO notificationRequest = new NotificationDTO(message, user.getEmail());

        ResponseEntity<String> response = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

        if ( !(response.getStatusCode() == HttpStatus.OK) ){
            throw new Exception("Error to send notification");
        }
    }
}
