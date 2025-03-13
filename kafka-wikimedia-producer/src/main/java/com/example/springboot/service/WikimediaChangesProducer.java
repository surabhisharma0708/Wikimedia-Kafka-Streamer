package com.example.springboot.service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

@Service
public class WikimediaChangesProducer 
{
	
	private static final Logger logger = LoggerFactory.getLogger(WikimediaChangesProducer.class);
	
	private KafkaTemplate<String, String> kafkaTemplate;
	@Value("${spring.kafka.topic}")
	private String topic;
	
	public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) 
	{
		
		this.kafkaTemplate = kafkaTemplate;
		
	}
	
	public void publish() throws InterruptedException
	{
		// so here we are going to use event-source to read the real-time data.
		EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate,topic);
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
		EventSource eventSource = builder.build();
		eventSource.start();
		TimeUnit.MINUTES.sleep(10);
	}
	
	
}
