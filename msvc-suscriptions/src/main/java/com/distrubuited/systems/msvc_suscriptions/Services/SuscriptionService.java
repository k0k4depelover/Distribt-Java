package com.distrubuited.systems.msvc_suscriptions.Services;

import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.distrubuited.systems.msvc_suscriptions.Entities.Email;
import com.distrubuited.systems.msvc_suscriptions.Repositories.EmailRepository;
import com.distrubuited.systems.msvc_suscriptions.dto.SuscriptionDto;
import com.distrubuited.systems.msvc_suscriptions.dto.UnsuscriptionDto;

import jakarta.transaction.Transactional;


/*

 Este service se encarga de postear las suscripciones hacia otro microservicio.
 Aca unicamente nos concentramos en postear las peticiones usando rabbitTemplate.
 Ademas de esta clase se debe configurar un puerto y un host para rabbitQM.
 Y finalmente una clase de configuracion
*/

@Service
public class SuscriptionService {
    public final RabbitTemplate rabbitTemplate;
    public final EmailRepository emailRepository;
    public SuscriptionService(RabbitTemplate rabbitTemplate, EmailRepository emailRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.emailRepository= emailRepository;
    }

    @Transactional
    public void publishSuscribe(SuscriptionDto dto){
        Optional <Email> emailSub = emailRepository.findByEmail(dto.email());
        if(emailSub.isPresent()){
              throw new IllegalStateException("El correo " + dto.email() + " ya está registrado.");
         }
        Email newEmail = new Email();
        newEmail.setEmail(dto.email());
        emailRepository.save(newEmail);
        rabbitTemplate.convertAndSend("suscription.exchange", "suscription.created", dto);
        
        }
        
   


    @Transactional
    public void publishUnsuscribe(UnsuscriptionDto dto){
        Optional <Email> emailSub = emailRepository.findByEmail(dto.email());
        if(emailSub.isEmpty()){
            throw new IllegalStateException("El correo " + dto.email() + " no esta registrado... ");
        }
        
        emailRepository.delete(emailSub.get());
        rabbitTemplate.convertAndSend("suscription.exchange", "suscription.deleted", dto);
        
        
    }
    
}
