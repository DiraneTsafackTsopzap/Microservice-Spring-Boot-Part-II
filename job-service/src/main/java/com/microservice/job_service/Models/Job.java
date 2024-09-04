package com.microservice.job_service.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Job {

    @Id
    public String id = UUID.randomUUID().toString();

    public String titre;
    public String description;
    public String minSalary;
    public String maxSalary;
    public String location;
    public String compagnyid;



}