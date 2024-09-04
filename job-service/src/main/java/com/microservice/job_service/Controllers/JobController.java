package com.microservice.job_service.Controllers;



import com.microservice.job_service.Models.Job;
import com.microservice.job_service.Models.JobWithCompagny;
import com.microservice.job_service.Repositories.JOB_REPOSITORIES;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 1- Placer cette annotation RestController
@RequestMapping("/jobs")   // 2-  Placer la requete mappings
public class JobController {


    // 3-  Appeler notre JobRepositories ici
    private final JOB_REPOSITORIES repositories;



    public JobController(JOB_REPOSITORIES jobRepositories) {

        this.repositories = jobRepositories;

    }

//    @GetMapping
//    public ResponseEntity<List<Job>> findAll(){
//        return ResponseEntity.ok(repositories.Find_All_Job());
//    }

    @GetMapping
    public ResponseEntity<List<JobWithCompagny>> findAll(){
      return ResponseEntity.ok(repositories.Find_All_Job_With_Compagny());
}



    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        repositories.Create_Job(job);
        return new ResponseEntity<>("Job Creer Avec Succes", HttpStatus.CREATED);
    }







    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable String id){
        Job job = repositories.get_Job_By_Id(id);
        if(job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable String  id){
        boolean deleted = repositories.Delete_Job_By_Id(id);
        if (deleted)
            return new ResponseEntity<>("Job Supprimer avec  Succes",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable String id,
                                            @RequestBody Job updatedJob){
        boolean updated = repositories.Update_Job(id, updatedJob);
        if (updated)
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
