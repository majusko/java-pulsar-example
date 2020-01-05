package io.github.majusko.java.pulsar.example.consumer;

import io.github.majusko.java.pulsar.example.configuration.Topics;
import io.github.majusko.pulsar.annotation.PulsarConsumer;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @PulsarConsumer(topic= Topics.STRING, clazz=String.class)
    public void consume(String message){

    }
}
