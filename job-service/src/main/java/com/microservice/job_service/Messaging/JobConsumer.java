package com.microservice.job_service.Messaging;


import com.microservice.job_service.Models.Job;
import com.microservice.job_service.Repositories.I_JOB_REPOSITORIES;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class JobConsumer {

    private I_JOB_REPOSITORIES jobRepositories;

    public JobConsumer(I_JOB_REPOSITORIES jobRepositories) {
        this.jobRepositories = jobRepositories;
    }

    @RabbitListener(queues = "JobQueue")
    public void ConsumeMessage(Job myjob)
    {
        jobRepositories.Create_Job(myjob);
    }
}
