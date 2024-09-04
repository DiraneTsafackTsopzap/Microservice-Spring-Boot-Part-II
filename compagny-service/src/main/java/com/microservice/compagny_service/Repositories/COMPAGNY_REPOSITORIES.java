package com.microservice.compagny_service.Repositories;


import com.microservice.compagny_service.JPA_Compagny_Repository.JPA_COMPAGNY_REPOSITORY;
import com.microservice.compagny_service.Messaging.JobProducer;
import com.microservice.compagny_service.Models.Compagny;
import com.microservice.compagny_service.Models.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 1- placer cette annotation
public class COMPAGNY_REPOSITORIES implements  I_COMPAGNY_REPOSITORIES {


    // 2- Appel de notre Jpa Repository ici
    JPA_COMPAGNY_REPOSITORY compagnyRepository;
    private final JobProducer jobProducer;



//  3- Creation du Cnstructeur d'abord et placer Jobrepository a linterieur

    @Autowired
    public COMPAGNY_REPOSITORIES(JPA_COMPAGNY_REPOSITORY compagnyRepository, JobProducer jobProducer)
    {
        this.compagnyRepository = compagnyRepository;
        this.jobProducer = jobProducer;
    }




    @Override
    public List<Compagny> Find_All_Compagny() {
        return compagnyRepository.findAll();
    }

    @Override
    public void Create_Compagny(Compagny new_compagny) {


        // Sauvegarde de l'entreprise dans le repository
        compagnyRepository.save(new_compagny);
    }

    @Override
    public Compagny get_Compagny_By_Id(String id) {
        return compagnyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean Delete_Compagny_By_Id(String id) {
        try {
            compagnyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean Update_Compagny(String id, Compagny compagnytoupdate) {
        Optional<Compagny> compagnyOptional = compagnyRepository.findById(id);
        if (compagnyOptional.isPresent()) {
            Compagny job = compagnyOptional.get();
            job.name = compagnytoupdate.name;
            job.description = compagnytoupdate.description;
            compagnyRepository.save(job); // Sauvegarde de l'entité mise à jour
            return true;
        }
        return false;
    }

    @Override
    public boolean isCompagnyExists(String id) {
        return compagnyRepository.findById(id).isPresent();
    }


}
