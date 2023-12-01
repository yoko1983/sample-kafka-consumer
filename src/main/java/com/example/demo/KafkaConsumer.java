package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;

import lombok.Data;

@Data
public class KafkaConsumer implements AcknowledgingMessageListener<String, String> {

	private String consumerName;

//	@Override
//	public void onMessage(ConsumerRecord<String, String> record) {
//		try {
//
//			System.out.println(
//					"consumername" + ": " + consumerName
//							+ "(" + Thread.currentThread().getId() + ")"   
//							+ ", topic: " 
//							+ record.topic() 
//							+ ", partition: " 
//							+ record.partition() 
//							+ ", offset: " 
//							+ record.offset() 
//							+ ", key: " 
//							+ record.key() 
//							+ ", value: " 
//							+ record.value());
//
//		} catch (Exception e) {
//			System.out.println("consumername" + ": " + Thread.currentThread().getId() + " Exception");
//		}			
//	}

	@Override
	public void onMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
		try {
			System.out.println(
					"consumername" + ": " + consumerName
					+ "(" + Thread.currentThread().getId() + ")"   
							+ ", topic: " 
							+ record.topic() 
							+ ", partition: " 
							+ record.partition() 
							+ ", offset: " 
							+ record.offset() 
							+ ", key: " 
							+ record.key() 
							+ ", value: " 
							+ record.value());
			acknowledgment.acknowledge();

		} catch (Exception e) {
			System.out.println("consumername" + ": " + Thread.currentThread().getId() + " Exception");
		}			
	}


}
