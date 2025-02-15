package com.microservice.compagny_service.Messaging;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;



    @Configuration
    public class RabbitMqConfiguration {

        @Bean
        public Queue addjobQueue() {
            return new Queue("JobQueue");
        }

        @Bean
        public Jackson2JsonMessageConverter jsonMessageConverter() {
            return new Jackson2JsonMessageConverter();
        }
        @Bean
        public RabbitTemplate rabbittemplate(ConnectionFactory connectionFactory) {

            RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setMessageConverter(jsonMessageConverter());
            return rabbitTemplate;
        }

}
