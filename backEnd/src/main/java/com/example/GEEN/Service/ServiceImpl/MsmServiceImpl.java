package com.example.GEEN.Service.ServiceImpl;

import com.example.GEEN.Service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MsmServiceImpl implements MsmService{
    @Autowired
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String senderName;

    @Override
    public Boolean send(String email, String title, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setFrom(senderName);
            message.setTo(email);
            message.setSubject(title);
            message.setText(code);
            mailSender.send(message);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }
}
