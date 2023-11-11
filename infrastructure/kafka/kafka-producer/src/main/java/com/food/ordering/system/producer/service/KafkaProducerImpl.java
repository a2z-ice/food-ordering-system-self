package com.food.ordering.system.producer.service;

import com.food.ordering.system.producer.exception.KafkaProducerException;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PreDestroy;
import java.io.Serializable;

@Slf4j
@Component
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K,V>{
    private final KafkaTemplate kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, K key, V message, ListenableFutureCallback<SendResult<K, V>> callback) {
        log.info("Sending message={} to Topic={}", message, topicName);
        try {
            final ListenableFuture<SendResult<K,V>> kafkaResultFuture = this.kafkaTemplate.send(topicName, key, message);
            kafkaResultFuture.addCallback(callback);
        } catch (KafkaException e){
            log.error("Error on kafka producer with key: {}, message: {} and exception: {}", key, message,
                    e.getMessage());
            throw new KafkaProducerException("Error on kafka producer with key: " + key + " and message: " + message);
        }
    }
    @PreDestroy
    public void close(){
        if(this.kafkaTemplate != null){
            log.info("Closing kafka producer!");
            this.kafkaTemplate.destroy();
        }
    }
}
