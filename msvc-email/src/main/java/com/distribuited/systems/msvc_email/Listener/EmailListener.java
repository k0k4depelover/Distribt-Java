package main.java.com.distribuited.systems.msvc_email.Listener;

import com.distribuited.systems.msvc_email.dto.SuscriptionDto;

@Component
public class EmailListener {
    
    public final EmailService emailService;

    @RabbitListener(queues = "suscription-queue")
    public void onSuscribe(SuscriptionDto dto){
        emailService.sendWelcomeEmail(dto);
    }

    public void onUnsuscribe(queues= "unsuscription-queue")
    public void onUnsuscribe(UnsuscriptionDto dto){
        emailService.sendGoodByeEmail(dto);
    }
}
