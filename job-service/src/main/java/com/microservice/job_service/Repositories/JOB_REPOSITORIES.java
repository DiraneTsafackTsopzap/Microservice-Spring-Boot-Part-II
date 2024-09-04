package com.microservice.job_service.Repositories;


import com.microservice.job_service.Clients.CompagnyClient;
import com.microservice.job_service.JPA_Job_Repository.JPA_JOB_REPOSITORY;
import com.microservice.job_service.Mapper.JobMapper;
import com.microservice.job_service.Models.Job;
import com.microservice.job_service.Models.JobWithCompagny;
import com.microservice.job_service.external.Compagny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service  // 1- Placer L'annotation Service ici
public class JOB_REPOSITORIES implements  I_JOB_REPOSITORIES{


    // 2- Appel de notre Jpa Repository ici
    JPA_JOB_REPOSITORY jobRepository;




//  3- Creation du Cnstructeur d'abord et placer Jobrepository a linterieur

    //4-  Appel de mon interface openfeing compagny client
    private CompagnyClient compagnyClient;
    @Autowired
    public JOB_REPOSITORIES(JPA_JOB_REPOSITORY jobRepository , CompagnyClient compagnyClient)
    {
        this.jobRepository = jobRepository;
        this.compagnyClient = compagnyClient;


    }


    // 4- Appeler mon Resttamplate defini ds le Appconfig  situer ds le Dossier Configuration ici
    @Autowired
    RestTemplate restTemplate;




    //
    @Override
    public List<Job> Find_All_Job() {
        return jobRepository.findAll();
    }










    @Override
    public void Create_Job(Job job) {

         //jobRepository.save(job);
        // 1. Vérifier si l'ID de l'entreprise existe
        Compagny compagny;
        try {
            compagny = compagnyClient.getCompagny(job.compagnyid);
        } catch (Exception e) {
            // 2. Si l'entreprise n'existe pas, lancer une exception
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "La CompagnyId" + job.compagnyid + " n'existe pas , Veuillez svp entrer une CompagnyId Correcte"
            );
        }

        // 3. Si l'entreprise existe, enregistrer le job
        jobRepository.save(job);
    }

    @Override
    public Job get_Job_By_Id(String id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean Delete_Job_By_Id(String id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean Update_Job(String id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.titre = updatedJob.titre;
            job.description = updatedJob.description;
            job.maxSalary = updatedJob.maxSalary;
            job.minSalary = updatedJob.minSalary;
            job.location = updatedJob.location;
            jobRepository.save(job); // Sauvegarde de l'entité mise à jour
            return true;
        }
        return false;
    }



//    Poml.xml de JobService - 10- L#annoter avec companyBreaker
    //  11- FallBack Mecanimus

    @Override
    public List<JobWithCompagny> Find_All_Job_With_Compagny() {

        // 1- Récupérer tous les Jobs
        List<Job> listes_Jobs = jobRepository.findAll();

        // 2- Creation d'une liste de  liste_de_job_avec_compagnies
        List<JobWithCompagny> Listes_Jobs_Avec_Compagnies = new ArrayList<>();

        for (Job job : listes_Jobs) {

             Compagny compagny = compagnyClient.getCompagny(job.compagnyid);
             JobWithCompagny jobWithCompagny = JobMapper.map_job_with_compagny(job,compagny);
             Listes_Jobs_Avec_Compagnies.add(jobWithCompagny);

        }

        return Listes_Jobs_Avec_Compagnies;


    }


}



