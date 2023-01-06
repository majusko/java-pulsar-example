package io.github.majusko.java.pulsar.example;

import io.github.majusko.java.pulsar.example.consumer.ConsumerService;
import io.github.majusko.java.pulsar.example.producer.ProducerService;
import org.apache.pulsar.client.api.PulsarClientException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PulsarContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@Testcontainers
class JavaPulsarExampleApplicationTests {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ConsumerService consumerService;

    @Container
    static PulsarContainer pulsarContainer = new PulsarContainer(DockerImageName.parse("apachepulsar/pulsar:latest"))
        .waitingFor(Wait.forListeningPort());

    @DynamicPropertySource
    static void propertySettings(DynamicPropertyRegistry registry) {
        registry.add("pulsar.serviceUrl", pulsarContainer::getPulsarBrokerUrl);
    }

    @Test
    void stringTest() throws PulsarClientException {
        await().untilFalse(consumerService.stringReceived);

        producerService.sendStringMsg();

        await().untilTrue(consumerService.stringReceived);
    }

    @Test
    void classTest() throws PulsarClientException {
        await().untilFalse(consumerService.classReceived);

        producerService.sendClassMsg();

        await().untilTrue(consumerService.classReceived);
    }
}
