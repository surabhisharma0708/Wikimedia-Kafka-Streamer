package com.example.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;




public class WikimediaChangesHandler implements EventHandler
{
	
	private static final Logger logger = LoggerFactory.getLogger(WikimediaChangesHandler.class);
	
	
	@Value("${spring.kafka.topic}")
	private String topic;
	
	private KafkaTemplate<String, String> kafkaTemplate;

	

	public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
		
		this.kafkaTemplate = kafkaTemplate;
		this.topic = topic;
	}

	@Override
	public void onOpen() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClosed() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(String event, MessageEvent messageEvent) throws Exception {
		// TODO Auto-generated method stub
		logger.info(String.format("event data %s", messageEvent.getData()));
		
		kafkaTemplate.send(topic, messageEvent.getData());
				
	}

	@Override
	public void onComment(String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
