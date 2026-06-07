package com.distrubuited.systems.msvc_suscribtions.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.distrubuited.systems.msvc_suscribtions.Services.SuscriptionsService;
import com.distrubuited.systems.msvc_suscribtions.dto.SuscriptionDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/suscriptions")
public class SuscriptionController {
    public final SuscriptionsService service;

    public SuscriptionController(SuscriptionsService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<Boolean>  postSuscription(@RequestBody SuscriptionDto suscription) {
        service.suscribe(suscription);
        return ResponseEntity.accepted().body(true);
   
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteSuscription(@RequestBody SuscriptionDto suscription){
        service.unsuscribe(suscription);
        return ResponseEntity.accepted().body(true);
    }
    
}
