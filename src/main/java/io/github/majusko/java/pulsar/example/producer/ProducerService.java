package io.github.majusko.java.pulsar.example.producer;

import io.github.majusko.java.pulsar.example.configuration.Topics;
import io.github.majusko.java.pulsar.example.data.MyMsg;
import io.github.majusko.pulsar.producer.PulsarTemplate;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final PulsarTemplate<String> stringProducer;

    private final PulsarTemplate<MyMsg> classProducer;

    public ProducerService(PulsarTemplate<String> stringProducer, PulsarTemplate<MyMsg> classProducer) {
        this.stringProducer = stringProducer;
        this.classProducer = classProducer;
    }

    public void sendStringMsg() throws PulsarClientException {
        stringProducer.send(Topics.STRING, "Hello World String!");
    }

    public void sendClassMsg() throws PulsarClientException {
        classProducer.send(Topics.CLASS, new MyMsg("Hello World Class!"));
    }
}
