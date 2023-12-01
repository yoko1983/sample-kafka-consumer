package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "kafka")
@Data
public class KafkaSettings {
	private String bootstrapServers;
	private String groupId;
	private String topic;
}
