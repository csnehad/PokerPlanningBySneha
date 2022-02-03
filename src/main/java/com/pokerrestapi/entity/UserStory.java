package com.pokerrestapi.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
@Entity
@Table(name = "UserStory")
public class UserStory {

	@Id
	@Column(name = "uStoryId", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String uStoryId;

	@Column(name = "description")
	private String description;

	@Column(name = "count")
	private Integer count;

	@Enumerated(EnumType.STRING)
	private VotingStatus votingStatus;

	public VotingStatus getVotingStatus() {
		return votingStatus;
	}

	public void setVotingStatus(VotingStatus votingStatus) {
		this.votingStatus = votingStatus;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "sessionId")
	private Session session;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "uStoryId")
	private Set<MemberUserStory> memberUstory;

	public Set<MemberUserStory> getMemberUstory() {
		return memberUstory;
	}

	public void setMemberUstory(Set<MemberUserStory> memberUstory) {
		this.memberUstory = memberUstory;
	}

	public UserStory() {
		super();
	}

	public UserStory(String uStoryId, String description) {
		super();
		this.uStoryId = uStoryId;
		this.description = description;
	}

	public UserStory(String uStoryId, String description, Integer count) {
		super();
		this.uStoryId = uStoryId;
		this.description = description;
		this.count = count;
	}

	public String getuStoryId() {
		return uStoryId;
	}

	public void setuStoryId(String uStoryId) {
		this.uStoryId = uStoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
