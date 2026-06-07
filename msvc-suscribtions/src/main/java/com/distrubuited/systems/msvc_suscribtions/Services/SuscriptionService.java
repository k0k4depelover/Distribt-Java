package com.distrubuited.systems.msvc_suscribtions.Services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.distrubuited.systems.msvc_suscribtions.dto.SuscriptionDto;
import com.distrubuited.systems.msvc_suscribtions.dto.UnsuscriptionDto;


/*

 Este service se encarga de postear las suscripciones hacia otro microservicio.
 Aca unicamente nos concentramos en postear las peticiones usando rabbitTemplate.
 Ademas de esta clase se debe configurar un puerto y un host para rabbitQM.
 Y finalmente una clase de configuracion
*/

@Service
public class SuscriptionService {
    public final RabbitTemplate rabbitTemplate;

    public SuscriptionService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishSuscribe(SuscriptionDto message){
        rabbitTemplate.convertAndSend("subscription.exchange", "subscription.created", message);
    }

    public void publishUnsuscribe(UnsuscriptionDto message){
        rabbitTemplate.convertAndSend("subscription.exchange", "subscription.deleted", message);
    }
    
}
