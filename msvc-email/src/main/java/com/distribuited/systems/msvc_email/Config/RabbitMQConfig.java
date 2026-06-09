package main.java.com.distribuited.systems.msvc_email.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    @Bean
    public MessageConverter JsonMessageConverter(){
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
    public Binding suscriptionBinding(TopicExchange suscriptionExchange, Queue suscriptionQueue){
        return BindingBuilder.bind(suscriptionQueue)
        .to(suscriptionExchange)
        .with("suscription.created");
    }

    @Bean
    public Binding unsuscriptionBinding(TopicExchange suscriptionExchange, Queue unsuscriptionQueue){
        return BindingBuilder.bind(unsuscriptionQueue)
        .to(suscriptionExchange)
        .with("suscription.deleted");
    }
    
}