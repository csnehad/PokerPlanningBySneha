package com.pokerrestapi.dto;

public class AddUserStoryRequest {

	String description;
	String sessionId;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSession(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
