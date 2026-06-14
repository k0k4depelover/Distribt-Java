package com.distrubuited.systems.msvc_suscriptions.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distrubuited.systems.msvc_suscriptions.Services.SuscriptionService;
import com.distrubuited.systems.msvc_suscriptions.dto.SuscriptionDto;
import com.distrubuited.systems.msvc_suscriptions.dto.UnsuscriptionDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/suscriptions")
public class SuscriptionController {

    public final SuscriptionService suscriptionService;
    
    public SuscriptionController(SuscriptionService suscriptionService) {
        this.suscriptionService = suscriptionService;
    }

    @PostMapping("/suscribe")
    public ResponseEntity<String> suscription(@RequestBody SuscriptionDto dto) {
        try {
            suscriptionService.publishSuscribe(dto);
            return ResponseEntity.ok("Suscripcion procesando...");}
        catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    
    @PostMapping("/unsuscribe")
    public ResponseEntity<String> unsuscription(@RequestBody UnsuscriptionDto dto) {
       
       try  { suscriptionService.publishUnsuscribe(dto);
        return ResponseEntity.ok("Desuscripcion procesando...");
    }catch(IllegalStateException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    }
        



}
