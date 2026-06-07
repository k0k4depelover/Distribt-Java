package com.distrubuited.systems.msvc_suscribtions.Services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.distrubuited.systems.msvc_suscribtions.dto.SuscriptionDto;

@Service
public class SuscriptionsService {
    private final RabbitTemplate rabbitTemplate;

    public SuscriptionsService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void suscribe(SuscriptionDto dto){
        rabbitTemplate.convertAndSend("suscription.exchange", "suscription.created", dto);

    }

    public void unsuscribe(SuscriptionDto dto){
        rabbitTemplate.convertAndSend("suscription.exchange", "suscription.deleted", dto);

    }
    
}
