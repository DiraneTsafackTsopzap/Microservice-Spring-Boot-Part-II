package com.microservice.job_service.Clients;


import com.microservice.job_service.external.Compagny;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "COMPAGNY-SERVICE")
public interface CompagnyClient {
    @GetMapping("/compagny/{id}")
    Compagny getCompagny(@PathVariable("id") String id);
}

// Attention : COMPAGNY-SERVICE est le nom de notre Service et est visible ds notre Registre au port 8761
// Cependant si on place le nom ici , on va devoir ajouter quelques configurations
// creation de la classe Appconfig