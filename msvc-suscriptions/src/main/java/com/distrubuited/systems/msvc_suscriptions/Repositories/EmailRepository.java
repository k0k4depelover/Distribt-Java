package com.distrubuited.systems.msvc_suscriptions.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.distrubuited.systems.msvc_suscriptions.Entities.Email;


public interface EmailRepository extends CrudRepository<Email, Long>{

    Optional<Email> findByEmail(String email);
    
}
