package com.distrubuited.systems.msvc_suscribtions.Listener;

import org.springframework.stereotype.Component;

import com.distrubuited.systems.msvc_suscribtions.dto.SuscriptionDto;
import com.distrubuited.systems.msvc_suscribtions.dto.UnsuscriptionDto;

@Component
public class EmailListener {
    public void onSuscription(SuscriptionDto suscription){
        // Aca va la logica, quizas un servicio que envie un email
    }

    public void onUnsuscription(UnsuscriptionDto unsuscription){
        // Aca va la logica, quizas un email de despedida.
    }
}
