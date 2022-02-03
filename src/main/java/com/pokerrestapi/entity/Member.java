package com.pokerrestapi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String memberId;

	@Column(name = "name")
	private String name;

	@Column(name = "status")
	private String memberStatus;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "sessionId")
	private Session session;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "memberId")
	private Set<MemberUserStory> memberUstory;

	public Member() {
		super();
	}

	public Member(String name, String memberStatus) {
		super();
		this.name = name;
		this.memberStatus = memberStatus;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Set<MemberUserStory> getMemberUstory() {
		return memberUstory;
	}

	public void setMemberUstory(Set<MemberUserStory> memberUstory) {
		this.memberUstory = memberUstory;
	}

}
