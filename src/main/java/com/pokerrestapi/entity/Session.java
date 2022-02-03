package com.pokerrestapi.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
@Entity
@Table(name = "PokerSession")
public class Session {

	@Id
	@Column(name = "sessionId")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String sessionId;

	@Column(name = "title")
	private String title;

	@Column(name = "deckType")
	private String deckType;

	@Column(name="status")
	private boolean isActive;

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sessionId")
	private Set<UserStory> userStories;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sessionId")
	private Set<Member> members;


	public Session() {
		super(); 
	}

	public Session(String title, String deckType) {
		super(); 
		this.title = title;
		this.deckType = deckType;
	}

	public Session(String sessionId, String title, String deckType, Set<UserStory> userStories, Set<Member> members){
		super();
		this.sessionId = sessionId;
		this.title = title;
		this.deckType = deckType;
		this.userStories = userStories;
		this.members = members;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDeckType() { 
		return deckType;
	}

	public void setDeckType(String deckType) { 
		this.deckType = deckType; 
	}

	public Set<UserStory> getUserStories() {
		return userStories;
	}

	public void setUserStories(Set<UserStory> userStories) { 
		this.userStories = userStories; 
	}

	public Set<Member> getMembers() {
		return members; 
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

}
