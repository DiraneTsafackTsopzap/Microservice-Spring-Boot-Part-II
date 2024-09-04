package com.microservice.job_service.JPA_Job_Repository;


import com.microservice.job_service.Models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 1- Placer cette annotation
public interface JPA_JOB_REPOSITORY extends JpaRepository<Job, String> {

}
