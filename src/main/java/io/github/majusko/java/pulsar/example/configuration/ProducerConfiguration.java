package io.github.majusko.java.pulsar.example.configuration;

import io.github.majusko.java.pulsar.example.data.MyMsg;
import io.github.majusko.pulsar.producer.ProducerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfiguration {

    @Bean
    public ProducerFactory producerFactory() {
        return new ProducerFactory()
            .addProducer(Topics.STRING, String.class)
            .addProducer(Topics.CLASS, MyMsg.class);
    }
}
