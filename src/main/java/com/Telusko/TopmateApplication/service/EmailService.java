package com.Telusko.TopmateApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException {
        // Create a MimeMessage
        MimeMessage message = javaMailSender.createMimeMessage();

        // Use MimeMessageHelper to enable HTML content
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Set email details
        helper.setTo(to);                  // Recipient
        helper.setSubject(subject);        // Email Subject
        helper.setText(htmlBody, true);    // Email Body (true = HTML content)

        // Send the email
        javaMailSender.send(message);
    }
}
