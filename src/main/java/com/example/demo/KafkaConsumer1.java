package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "kafka1.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaConsumer1 {
	
	@Value("${kafka1.name:NoName}")
	private String consumerName;
	
	@KafkaListener(topics = "${kafka1.topic}", concurrency =  "${kafka1.concurrency}")
	public void consume1(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
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
