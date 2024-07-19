package com.bootsandcodes.fashiondealfinder.publisher;

import com.bootsandcodes.fashiondealfinder.model.Deal;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
        try {
            sendHtmlMessage("rasula.caldera@gmail.com", subject, text);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        emailSender.send(message);
    }

    private String buildEmailContent(List<Deal> deals) {
        StringBuilder content = new StringBuilder();
        content.append("<html><body>");
        content.append("<h1>Hello,</h1>");
        content.append("<p>Here are the latest deals from Patagonia:</p>");
        content.append("<ul>");

        for (Deal deal : deals) {
            content.append("<li>");
            content.append("<h2>").append(deal.getTitle()).append("</h2>");
            content.append("<p><strong>Price:</strong> €").append(deal.getPrice()).append("</p>");
            content.append("<p><strong>Original Price:</strong> €").append(deal.getOriginalPrice()).append("</p>");
            content.append("<p><strong>Discount Rate:</strong> ").append(deal.getDiscount()).append("%</p>");
            content.append("<p><a href=\"").append(deal.getUrl()).append("\">View Deal</a></p>");
            content.append("<p><img src=\"").append(deal.getImageUrl()).append("\" alt=\"").append(deal.getTitle()).append("\" style=\"width:100px;height:auto;\"></p>");
            content.append("</li>");
        }

        content.append("</ul>");
        content.append("<p>Best Regards,<br>Your Fashion Deal Finder</p>");
        content.append("</body></html>");

        return content.toString();
    }
}
