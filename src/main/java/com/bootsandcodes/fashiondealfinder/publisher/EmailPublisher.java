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
        content.append("<html><body style=\"font-family: Arial, sans-serif;\">");
        content.append("<h1 style=\"color: #2c3e50;\">Hello,</h1>");
        content.append("<p style=\"color: #34495e;\">Here are the latest deals from Patagonia:</p>");
        content.append("<table style=\"width: 100%; border-collapse: collapse;\">");

        int columnsPerRow = 4;
        int colCounter = 0;

        for (Deal deal : deals) {
            if (colCounter % columnsPerRow == 0) {
                if (colCounter > 0) {
                    content.append("</tr>");
                }
                content.append("<tr>");
            }

            content.append("<td style=\"padding: 10px; vertical-align: top;\">");
            content.append("<a href=\"").append(deal.getUrl()).append("\" style=\"text-decoration: none; color: inherit;\">");
            content.append("<div style=\"box-shadow: 0 4px 8px rgba(0,0,0,0.1); padding: 10px; border-radius: 5px;\">");
            content.append("<h2 style=\"color: #16a085;\">").append(deal.getTitle()).append("</h2>");
            content.append("<p><strong>Price:</strong> €").append(deal.getPrice()).append("</p>");
            content.append("<p><strong>Original Price:</strong> €").append(deal.getOriginalPrice()).append("</p>");
            content.append("<p><strong>Discount Rate:</strong> ").append(deal.getDiscount()).append("%</p>");
            content.append("<p><img src=\"").append(deal.getImageUrl()).append("\" alt=\"").append(deal.getTitle()).append("\" style=\"width:200px; height:auto; border-radius: 5px;\"></p>");
            content.append("</div>");
            content.append("</a>");
            content.append("</td>");

            colCounter++;
        }

        if (colCounter > 0 && colCounter % columnsPerRow != 0) {
            content.append("</tr>");
        }

        content.append("</table>");
        content.append("<p style=\"color: #34495e;\">Best Regards,<br>Your Fashion Deal Finder</p>");
        content.append("</body></html>");

        return content.toString();
    }

}
