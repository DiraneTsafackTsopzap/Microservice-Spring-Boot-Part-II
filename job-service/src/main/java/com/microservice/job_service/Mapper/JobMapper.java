package com.microservice.job_service.Mapper;


import com.microservice.job_service.Models.Job;
import com.microservice.job_service.Models.JobWithCompagny;
import com.microservice.job_service.external.Compagny;

public class JobMapper {

    public static JobWithCompagny map_job_with_compagny(Job job , Compagny compagny) {


        JobWithCompagny jobWithCompagny = new JobWithCompagny();
        jobWithCompagny.id = job.id;
        jobWithCompagny.titre = job.titre;
        jobWithCompagny.description = job.description;
        jobWithCompagny.location = job.location;
        jobWithCompagny.maxSalary = job.maxSalary;
        jobWithCompagny.minSalary = job.minSalary;
        jobWithCompagny.compagnyid = job.compagnyid;
        jobWithCompagny.Compagnies = compagny;

        return jobWithCompagny;
    }
}
