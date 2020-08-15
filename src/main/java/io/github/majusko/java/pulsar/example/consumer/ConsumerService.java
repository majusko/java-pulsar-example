package io.github.majusko.java.pulsar.example.consumer;

import io.github.majusko.java.pulsar.example.configuration.Topics;
import io.github.majusko.pulsar.annotation.PulsarConsumer;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ConsumerService {
    public AtomicBoolean received = new AtomicBoolean(false);

    @PulsarConsumer(topic= Topics.STRING, clazz=String.class)
    public void consume(String message){
        received.set(true);
    }
}
