package com.microservice.compagny_service.Controllers;


import com.microservice.compagny_service.Messaging.JobProducer;
import com.microservice.compagny_service.Models.Compagny;
import com.microservice.compagny_service.Models.Job;
import com.microservice.compagny_service.Repositories.I_COMPAGNY_REPOSITORIES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compagny")
public class CompagnyController {


    private I_COMPAGNY_REPOSITORIES my_repository;
    private final JobProducer jobProducer;





@Autowired
    public CompagnyController(I_COMPAGNY_REPOSITORIES my_repositor, JobProducer jobProducer) {
        this.jobProducer = jobProducer;
        this.my_repository = my_repositor;

    }

    // Envoie du Job ds RabbitMQ
    @PostMapping("/sendjob")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        try {
            // Vérifier si la compagnie existe
            boolean exists = my_repository.isCompagnyExists(job.compagnyid);

            if (!exists) {
                return ResponseEntity.status(404).body("Compagny avec ID " + job.compagnyid + " n'existe pas.");
            }

            // Envoyer le job à RabbitMQ
            jobProducer.send_job_to_rabbitmq(job);

            // Retourner une réponse de succès
            return ResponseEntity.ok("Job envoyé à RabbitMQ avec succès !");
        } catch (Exception e) {
            // Retourner une réponse d'erreur
            return ResponseEntity.status(500).body("Erreur lors de l'envoi du job : " + e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<String> createCompagny(@RequestBody Compagny newCompagny) {
        try {
            my_repository.Create_Compagny(newCompagny);
            return ResponseEntity.ok("Entreprise créée avec succès !");
        } catch (Exception e) {
            // Vous pouvez personnaliser le message d'erreur et le code de statut selon vos besoins
            return ResponseEntity.status(500).body("Erreur lors de la création de l'entreprise : " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compagny> get_compagny_id(@PathVariable String id){
        Compagny compagnyexiste = my_repository.get_Compagny_By_Id(id);
        if(compagnyexiste != null)
            return new ResponseEntity<>(compagnyexiste, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Compagny>> findAll(){
        return ResponseEntity.ok(my_repository.Find_All_Compagny());
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete_Compagny(@PathVariable String  id){
        boolean deleted = my_repository.Delete_Compagny_By_Id(id);
        if (deleted)
            return new ResponseEntity<>("Compagny Supprimer avec  Succes",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCompagny(@PathVariable String id,
                                                 @RequestBody Compagny updatedJob){
        boolean updated = my_repository.Update_Compagny(id, updatedJob);
        if (updated)
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }







}
