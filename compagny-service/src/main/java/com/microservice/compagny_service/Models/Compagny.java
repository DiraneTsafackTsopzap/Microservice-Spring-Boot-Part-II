package com.microservice.compagny_service.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


import java.util.UUID;


@Entity
public class Compagny {

    @Id
    public String id = UUID.randomUUID().toString();
    public String name;
    public String description;


  // Chaque Compagny a une Liste de Jobs Associes
//    @OneToMany
//    public List<Job> Listes_Jobs;
}
