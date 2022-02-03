package com.pokerrestapi.service;

import java.util.List;

import com.pokerrestapi.entity.Member;
import com.pokerrestapi.entity.Session;
import com.pokerrestapi.entity.UserStory;

public interface SessionService {
	Session createSession(Session session,String memberName);
	
	List<Session> getAllSessions();
	
	Session getSessionById(String sessionId);
	
	String getTitleOfTheSession(String sessionId);
	
	List<Member> addUpdateMember(String sessionId, String memberName);
	
	void destroySession(String sessionId);
	
	List<UserStory> getUserStoryListInSession(String sessionId);
	
	List<Member> getMembersInSession(String sessionId);
	
	List<Session> getAllActiveSessions();

}
