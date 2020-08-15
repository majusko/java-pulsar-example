package io.github.majusko.java.pulsar.example;

import io.github.majusko.java.pulsar.example.configuration.Topics;
import io.github.majusko.java.pulsar.example.consumer.ConsumerService;
import io.github.majusko.pulsar.producer.PulsarTemplate;
import org.apache.pulsar.client.api.PulsarClientException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PulsarContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@Testcontainers
class JavaPulsarExampleApplicationTests {

    @Autowired
    private PulsarTemplate<String> producer;

    @Autowired
    private ConsumerService consumerService;

    @Container
    static PulsarContainer pulsarContainer = new PulsarContainer();

    @DynamicPropertySource
    static void propertySettings(DynamicPropertyRegistry registry) {
        registry.add("pulsar.serviceUrl", pulsarContainer::getPulsarBrokerUrl);
    }

    @Test
    void contextLoads() throws PulsarClientException {
        await().untilFalse(consumerService.received);

        producer.send(Topics.STRING, "message");

        await().untilTrue(consumerService.received);
    }
}
