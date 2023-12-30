package com.courrier.courrierpublish.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue queue() {
        return new Queue("createdDelivery", true);
    }

    @Bean
    Queue bonusModifiedQueue() {
        return new Queue("bonusModified", true);
    }

    @Bean
    Queue adjustmentModifiedQueue() {
        return new Queue("adjustmentModified", true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("courrierchallenge");
    }

    @Bean
    Binding bindingCreatedDelivery(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("createdDelivery");
    }

    @Bean
    Binding bindingBonusModified(Queue bonusModifiedQueue, DirectExchange exchange) {
        return BindingBuilder.bind(bonusModifiedQueue).to(exchange).with("bonusModified");
    }

    @Bean
    Binding bindingAdjustmentModified(Queue adjustmentModifiedQueue, DirectExchange exchange) {
        return BindingBuilder.bind(adjustmentModifiedQueue).to(exchange).with("adjustmentModified");
    }
}
