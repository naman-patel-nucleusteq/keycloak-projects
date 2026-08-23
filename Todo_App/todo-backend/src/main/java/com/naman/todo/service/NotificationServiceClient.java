package com.naman.todo.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationServiceClient {

    public void sendNotification(String message) {
        System.out.println("Notification sent: " + message);
    }
}
