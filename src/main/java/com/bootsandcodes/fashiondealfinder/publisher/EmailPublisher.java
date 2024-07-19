package com.bootsandcodes.fashiondealfinder.publisher;

import com.bootsandcodes.fashiondealfinder.model.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailPublisher implements Publish {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void publish(List<Deal> deals) {
        String subject = "Latest Deals from Patagonia";
        String text = buildEmailContent(deals);
        sendSimpleMessage("rasula.caldera@gmail.com", subject, text);
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    private String buildEmailContent(List<Deal> deals) {
        StringBuilder content = new StringBuilder();
        content.append("Hello,\n\nHere are the latest deals from Patagonia:\n\n");

        for (Deal deal : deals) {
            content.append("Title: ").append(deal.getTitle()).append("\n");
            content.append("Price: ").append(deal.getPrice()).append("\n");
            content.append("Original Price: ").append(deal.getOriginalPrice()).append("\n");
            content.append("URL: ").append(deal.getUrl()).append("\n");
            content.append("Image: ").append(deal.getImageUrl()).append("\n\n");
        }

        content.append("Best Regards,\nYour Fashion Deal Finder");

        return content.toString();
    }
}
