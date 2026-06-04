package com.example.studio.service;

import com.example.studio.entity.Photo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class EmailService {

    @Autowired private JavaMailSender mailSender;
    @Autowired private TemplateEngine templateEngine;

    public void sendOrderConfirmation(String toEmail, String customerName, Long orderId,
                                      double total, List<Photo> photos, boolean embedLogo) {
        try {
            Context ctx = new Context();
            ctx.setVariable("customerName", customerName);
            ctx.setVariable("orderId", orderId);
            ctx.setVariable("email", toEmail);
            ctx.setVariable("total", total);
            ctx.setVariable("photos", photos);
            ctx.setVariable("year", java.time.Year.now().getValue());

            String html = templateEngine.process("email/order-confirmation", ctx);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject("Your PhotoStudio order — download links inside 📸");
            helper.setFrom("yourgmail@gmail.com", "PhotoStudio");
            helper.setText(html, true);

            if (embedLogo) {
                ClassPathResource logo = new ClassPathResource("static/images/logo.png");
                if (logo.exists()) helper.addInline("logoMail", logo);
            }
            mailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
