package com.distribuited.systems.msvc_email.Listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.distribuited.systems.msvc_email.Dto.SuscriptionDto;
import com.distribuited.systems.msvc_email.Dto.UnsuscriptionDto;
import com.distribuited.systems.msvc_email.Services.EmailService;
import org.springframework.stereotype.Component;


@Component
public class EmailListener {
    public final EmailService emailService;

    public EmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "suscription-queue")
    public void onSuscribe(SuscriptionDto dto){
        emailService.sendWelcomeEmail(dto.email());
    }
    
    @RabbitListener(queues="unsuscription-queue")
    public void onUnsuscribe(UnsuscriptionDto dto){
        emailService.sendGoodbyeEmail(dto.email());
    }
}
