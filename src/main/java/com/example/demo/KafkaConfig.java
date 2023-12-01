package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
@EnableKafka
@EnableConfigurationProperties({KafkaSettings.class})
public class KafkaConfig {
	@Bean
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
	kafkaListenerContainerFactory(KafkaSettings settings) {
		ConcurrentKafkaListenerContainerFactory<String, String> factory =
				new ConcurrentKafkaListenerContainerFactory<>();

		factory.setConsumerFactory(consumerFactory(settings));
		factory.getContainerProperties().setPollTimeout(3000);
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
//		factory.setConcurrency(3);
		return factory;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory(KafkaSettings settings) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, settings.getBootstrapServers());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, settings.getGroupId());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(props);
	}

}