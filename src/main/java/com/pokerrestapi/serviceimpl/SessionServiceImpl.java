package com.pokerrestapi.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokerrestapi.common.util.AppConstants;
import com.pokerrestapi.entity.Member;
import com.pokerrestapi.entity.Session;
import com.pokerrestapi.entity.UserStory;
import com.pokerrestapi.exception.SessionNotFoundException;
import com.pokerrestapi.repository.MemberRepository;
import com.pokerrestapi.repository.SessionRepository;
import com.pokerrestapi.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {

	Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	MemberRepository memberRepository;

	//creating new session
	@Override 
	public Session createSession(Session pokerSession,String memberName) {
		logger.info("createSession start");
		Session ps = null; 
		//checks for existing session 
		if(pokerSession.getTitle()!=null &&
				sessionRepository.existsByTitle(pokerSession.getTitle())!=null) {

			ps = sessionRepository.existsByTitle(pokerSession.getTitle()); 

		}else { 
			//if there is no session creating a new session
			Random random = new Random(); 
			int sessionId = random.nextInt(10000);
			String session = String.valueOf(sessionId);
			pokerSession.setSessionId(session); 
			//adding member along with voting status as not voted
			Member member = new Member(memberName,AppConstants.NOT_VOTED);
			Set<Member> members = new HashSet<Member>();
			members.add(member);
			pokerSession.setMembers(members);
			pokerSession.getTitle();
			pokerSession.setActive(true);
			ps = sessionRepository.save(pokerSession);
		}
		logger.info("createSession end");
		return ps;
	}

	//get all active and inactive sessions
	@Override
	public List<Session> getAllSessions() {
		return sessionRepository.findAll();
	}

	//get all active sessions with status true
	@Override
	public List<Session> getAllActiveSessions() {
		logger.info("getAllActiveSessions start");
		List<Session> sessions= sessionRepository.findByStatus(true);
		logger.info("getAllActiveSessions end");
		return sessions;
	}

	//get session by id
	@Override
	public Session getSessionById(String sessionId) {
		logger.info("getSessionById start");
		Session session=sessionRepository.getSessionById(sessionId);
		logger.info("getSessionById end");
		return session;
	}

	//get title of session
	@Override 
	public String getTitleOfTheSession(String sessionId) {
		logger.info("getTitleOfTheSession start");
		Session session =  sessionRepository.getById(sessionId);
		logger.info("getTitleOfTheSession end");
		return session.getTitle(); 
	}

	//adding member to session
	@Override
	public List<Member> addUpdateMember(String sessionId, String memberName) {
		logger.info("addUpdateMember start");
		//checking session is active or not
		Session pokerSession = sessionRepository.getById(sessionId);
		if(pokerSession!=null && !pokerSession.isActive()) {
			throw new SessionNotFoundException();
		}
		//creating new member
		Member member = new Member(memberName,AppConstants.NOT_VOTED);
		member.setSession(pokerSession);
		//adding member
		memberRepository.save(member);
		//get all member list
		Set<Member> memberSet = pokerSession.getMembers();
		List<Member> memberList = new ArrayList<Member>();
		memberList.addAll(memberSet);
		logger.info("addUpdateMember end");
		return memberList;
	}

	//destroy session and updating status as false
	@Override
	public void destroySession(String sessionId) {
		Session session= sessionRepository.getById(sessionId);
		session.setActive(false);
		sessionRepository.save(session);

	}

	//get userStory list in session
	@Override
	public List<UserStory> getUserStoryListInSession(String sessionId) {
		logger.info("getUserStoryListInSession start");
		Session pokerSession = sessionRepository.getById(sessionId);
		Set<UserStory> userStories = pokerSession.getUserStories();
		List<UserStory> userStoryList = new ArrayList<UserStory>();
		userStoryList.addAll(userStories);
		logger.info("getUserStoryListInSession end");
		return userStoryList;
	}
	
	//get members in session
	@Override
	public List<Member> getMembersInSession(String sessionId) {
		logger.info("getMembersInSession start");
		Session pokerSession = sessionRepository.getById(sessionId);
		Set<Member> members = pokerSession.getMembers();
		List<Member> memberList = new ArrayList<Member>();
		memberList.addAll(members);
		logger.info("getMembersInSession end");
		return memberList;
	}

}
