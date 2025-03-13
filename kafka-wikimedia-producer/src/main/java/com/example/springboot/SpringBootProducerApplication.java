package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springboot.service.WikimediaChangesProducer;

@SpringBootApplication
public class SpringBootProducerApplication implements CommandLineRunner
{
	
	@Autowired
	private WikimediaChangesProducer wikimediaProducerChanges;
	
	public static void main(String args[])
	{
		SpringApplication.run(SpringBootProducerApplication.class);
	}
	
	@Autowired
	private WikimediaChangesProducer wikimediaChangesProducer;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		wikimediaChangesProducer.publish();
		
	}
}
