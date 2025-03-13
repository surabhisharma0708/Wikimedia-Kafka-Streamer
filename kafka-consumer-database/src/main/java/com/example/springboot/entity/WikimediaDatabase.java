package com.example.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="wikimedia_recentchange")
public class WikimediaDatabase 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Lob
	private String wikiEventData;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getWikiEventData() {
		return wikiEventData;
	}
	public void setWikiEventData(String wikiEventData) {
		this.wikiEventData = wikiEventData;
	}
	
	
}
