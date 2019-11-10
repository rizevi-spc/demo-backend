package com.example.demo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roverConfig")
public class RoverConfig {

    @Id
    private String id;
    
    private int port;
    
    private LocationInfo lastLocationInfo;
    
    private boolean active;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocationInfo getLastLocationInfo() {
		return lastLocationInfo;
	}

	public void setLastLocationInfo(LocationInfo lastLocationInfo) {
		this.lastLocationInfo = lastLocationInfo;
	}
    
    

    
}
