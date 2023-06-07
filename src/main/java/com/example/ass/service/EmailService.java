package com.example.ass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendActivationEmail(String to, String activationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Kích hoạt tài khoản");
        message.setText("Xin chào,\n\nVui lòng nhấp vào liên kết sau để kích hoạt tài khoản của bạn: " + activationLink);
        mailSender.send(message);
    }
}
