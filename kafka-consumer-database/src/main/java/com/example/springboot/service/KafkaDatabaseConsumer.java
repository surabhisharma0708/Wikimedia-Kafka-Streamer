package com.example.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.springboot.entity.WikimediaDatabase;
import com.example.springboot.repository.WikimediaDataRepository;

@Service
public class KafkaDatabaseConsumer 
{
	private static final Logger logger = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
	
	private WikimediaDataRepository dataRepository;
	
	
	
	
	public KafkaDatabaseConsumer(WikimediaDataRepository dataRepository) 
	{
		this.dataRepository = dataRepository;
	}




	@KafkaListener(topics = "wikimedia-recentchange", groupId = "myGroup")
	public void consume(String eventMessage)
	{
		logger.info(String.format("Message is consumed %s", eventMessage));
		
		WikimediaDatabase data = new WikimediaDatabase();
		data.setWikiEventData(eventMessage);
		
		dataRepository.save(data);
		
	}
}
