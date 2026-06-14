package com.distribuited.systems.msvc_email.Configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.BindingBuilder;

@Configuration
public class RabbitQMConfig {
    

    // Para que los mensajes se desacoplen en JSON, asi cualquier
    // servicio puede leerlo, independientemente si es otro lenguaje
    @Bean
    public Jackson2JsonMessageConverter JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange suscriptionExchange(){
        return new TopicExchange("suscription.exchange");
    }

    @Bean
    public Queue suscriptionQueue(){
        return new Queue("suscription-queue");
    }


    @Bean
    public Queue unsuscriptionQueue(){
        return new Queue("unsuscription-queue");
    }

    @Bean
    public org.springframework.amqp.core.Binding suscriptioBinding(TopicExchange suscriptionExchange, Queue suscriptionQueue){
        return BindingBuilder.bind(suscriptionQueue)
                        .to(suscriptionExchange)
                        .with("suscription.created");
    }

    @Bean
    public org.springframework.amqp.core.Binding unsuscriptionBinding(TopicExchange suscriptionExchange, Queue unsuscriptionQueue){
        return BindingBuilder.bind(unsuscriptionQueue)
                .to(suscriptionExchange)
                .with("suscription.deleted");
    }
}
