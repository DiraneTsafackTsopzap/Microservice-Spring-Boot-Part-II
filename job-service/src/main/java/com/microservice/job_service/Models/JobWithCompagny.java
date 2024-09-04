package com.microservice.job_service.Models;


import com.microservice.job_service.external.Compagny;



// 1-  cette classe est une Data transfert Object Class qui nous retournera a la fois les infprmations en Json de notre
// Job ainsi que les compagny associes a ce Job
public class JobWithCompagny {

    public String id;

    public String titre;
    public String description;
    public String minSalary;
    public String maxSalary;
    public String location;
    public String compagnyid;

    public Compagny Compagnies;

}
