package com.pokerrestapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MemberUserStory")
public class MemberUserStory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String memberUstoryId;

	@ManyToOne
	@JoinColumn(name = "memberId")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "uStoryId")
	private UserStory userStory;

	@Column(name = "votePoint")
	private Integer votePoint;
	
	public MemberUserStory() {
		super();
	}

	public String getMemberUstoryId() {
		return memberUstoryId;
	}

	public void setMemberUstoryId(String memberUstoryId) {
		this.memberUstoryId = memberUstoryId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public UserStory getUserStory() {
		return userStory;
	}

	public void setUserStory(UserStory userStory) {
		this.userStory = userStory;
	}

	public Integer getVotePoint() {
		return votePoint;
	}

	public void setVotePoint(Integer votePoint) {
		this.votePoint = votePoint;
	}

}
