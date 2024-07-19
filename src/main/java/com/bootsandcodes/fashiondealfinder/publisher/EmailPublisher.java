package com.bootsandcodes.fashiondealfinder.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailPublisher implements Publish {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void publish() {
        sendSimpleMessage("rasula.caldera@gmail.com", "Test Subject", "Test email body");
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
