package com.distribuited.systems.msvc_email.Services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.distribuited.systems.msvc_email.Configuration.MailProperties;

@Service
public class EmailService {
    public final MailProperties mailProperties;
    public final JavaMailSender mailSender;

    EmailService(MailProperties mailProperties, JavaMailSender mailSender) {
        this.mailProperties = mailProperties;
        this.mailSender= mailSender;
    }   


    public void sendWelcomeEmail(String to){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(mailProperties.getUsername());
        message.setSubject("Bienvenido a Cermeño SA!");
        message.setText("Gracias por suscribirte a nuestros servicios...");
        mailSender.send(message);
    }

    public void sendGoodbyeEmail(String to){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(mailProperties.getUsername());
        message.setSubject("Lamentamos tu partida!");
        message.setText("Esperamos que regreses, gracias por usar nuestros servicios !");
        mailSender.send(message);
    }
}
  