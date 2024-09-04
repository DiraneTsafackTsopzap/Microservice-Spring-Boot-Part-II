package com.microservice.compagny_service.Messaging;

import com.microservice.compagny_service.Models.Job;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobProducer {

    private  final RabbitTemplate rabbitTemplate;

    public JobProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send_job_to_rabbitmq(Job job) {
        Job new_job = new Job();
        new_job.compagnyid = job.compagnyid;
        new_job.titre = job.titre;
        new_job.description = job.description;
        new_job.location = job.location;
        new_job.maxSalary = job.maxSalary;
        new_job.minSalary = job.minSalary;

        rabbitTemplate.convertAndSend("JobQueue", new_job);
    }
}
