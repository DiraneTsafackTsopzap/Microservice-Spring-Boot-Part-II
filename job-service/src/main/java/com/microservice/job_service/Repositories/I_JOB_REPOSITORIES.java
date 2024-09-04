package com.microservice.job_service.Repositories;


import com.microservice.job_service.Models.Job;
import com.microservice.job_service.Models.JobWithCompagny;

import java.util.List;

public interface I_JOB_REPOSITORIES {

    List<Job> Find_All_Job();
    void Create_Job(Job job);

    Job get_Job_By_Id(String id);

    boolean Delete_Job_By_Id(String id);

    boolean Update_Job(String id, Job updatedJob);

    List<JobWithCompagny> Find_All_Job_With_Compagny();
}
