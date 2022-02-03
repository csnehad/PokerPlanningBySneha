package com.pokerrestapi.dto;

public class SubmitVoteRequest {

	String uStoryId;
	String memberId;
	String sessionId;
	Integer votePoint;

	public String getuStoryId() {
		return uStoryId;
	}
	public void setuStoryId(String uStoryId) {
		this.uStoryId = uStoryId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSession(String sessionId) {
		this.sessionId = sessionId;
	}
	public Integer getVotePoint() {
		return votePoint;
	}
	public void setVotePoint(Integer votePoint) {
		this.votePoint = votePoint;
	}
}
