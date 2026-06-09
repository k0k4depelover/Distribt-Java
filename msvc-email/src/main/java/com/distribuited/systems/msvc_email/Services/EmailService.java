package main.java.com.distribuited.systems.msvc_email.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    public final MailProperties mailProperties;
    
    public final JavaMailSender mailSender;
    

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendWelcomeEmail(String to){
        SimpleMailMessage message= new SimpleMailMessage();

        message.setTo(to);
        message.setFrom(mailProperties.getUsername());
        message.setSubject("Bienvenido!");
        message.setText("Gracias por suscribirse a nuestro servicio!");
        mailSender.send(message);
    }
    

    public void sendGoodByeEmail(String to){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom(mailProperties.getUsername());
        message.setSubject("Te extrañaremos, esperamos que vuelvas!");
        message.setText("Gracias por haber utilizado nuestros servicios!");
        mailSender.send(message);
    }
}
