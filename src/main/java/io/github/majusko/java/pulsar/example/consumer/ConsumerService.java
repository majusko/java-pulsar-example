package io.github.majusko.java.pulsar.example.consumer;

import io.github.majusko.java.pulsar.example.configuration.Topics;
import io.github.majusko.java.pulsar.example.data.MyMsg;
import io.github.majusko.pulsar.annotation.PulsarConsumer;
import io.github.majusko.pulsar.constant.Serialization;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ConsumerService {
    public AtomicBoolean stringReceived = new AtomicBoolean(false);
    public AtomicBoolean classReceived = new AtomicBoolean(false);

    @PulsarConsumer(topic= Topics.STRING, clazz=String.class)
    public void consumeString(String message){
        System.out.println(message);
        stringReceived.set(true);
    }

    @PulsarConsumer(topic= Topics.CLASS, clazz = MyMsg.class, serialization = Serialization.JSON)
    public void consumeClass(MyMsg message){
        System.out.println(message.getData());
        classReceived.set(true);
    }
}
