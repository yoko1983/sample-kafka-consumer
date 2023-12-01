package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "kafka2.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaConsumer2 {

	@Value("${kafka2.name:NoName}")
	private String consumerName;
	
	@KafkaListener(topics = "${kafka2.topic}", concurrency =  "${kafka2.concurrency}")
	public void consume2(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
		consume(record, acknowledgment, consumerName);
		
	}

	public void consume(ConsumerRecord<String, String> record, Acknowledgment acknowledgment, String consumerName) {
		try {
			System.out.println(consumerName + ": " + Thread.currentThread().getId() + ", topic: " + record.topic() + ", partition: " + record.partition() + ", offset: " + record.offset() + ", key: " + record.key() + ", value: " + record.value());
			acknowledgment.acknowledge();

		} catch (Exception e) {
			System.out.println(consumerName + ": " + Thread.currentThread().getId() + " Exception");
		}		
		
		
	}

}
