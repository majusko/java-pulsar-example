package io.github.majusko.java.pulsar.example.error;

import io.github.majusko.pulsar.consumer.ConsumerAggregator;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@DependsOn({"consumerAggregator"})
public class PulsarErrorHandler {

    private final ConsumerAggregator consumerAggregator;

    public PulsarErrorHandler(ConsumerAggregator consumerAggregator) {
        this.consumerAggregator = consumerAggregator;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void pulsarErrorHandler() {
        consumerAggregator.onError(failedMessage -> failedMessage.getException().printStackTrace());
    }
}
