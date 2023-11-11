package com.food.ordering.system.kafka.consumer;

import com.food.ordering.system.kafka.config.data.KafkaConfigData;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;

import java.util.List;

public interface KafkaConsumer<T extends SpecificRecordBase> {
    void receive(List<T> message, List<Long> keys, List<Integer> partition, List<Long> offset);
}
