package com.pokerrestapi.controller;

import java.util.List;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokerrestapi.common.util.AppConstants;
import com.pokerrestapi.dto.AddMemberRequest;
import com.pokerrestapi.dto.CreatePokerSessionRequest;
import com.pokerrestapi.entity.Member;
import com.pokerrestapi.entity.Session;
import com.pokerrestapi.entity.UserStory;
import com.pokerrestapi.exception.MemberNotFoundException;
import com.pokerrestapi.exception.SessionNotFoundException;
import com.pokerrestapi.exception.UpdateMemberException;
import com.pokerrestapi.exception.UserStoryNotFoundException;
import com.pokerrestapi.service.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController {
	Logger logger = LoggerFactory.getLogger(SessionController.class);

	@Autowired
	SessionService sessionService;

	//creating a session 
	@PostMapping(AppConstants.API_CREATE_SESSION)
	public ResponseEntity<Session> createSession(@Validated @RequestBody CreatePokerSessionRequest pokSesReq) {
		logger.info("createSession start");
		try {

			Session session = sessionService.createSession(new Session(pokSesReq.getTitle(),pokSesReq.getDeckType()),pokSesReq.getMemberName());
			logger.info("createSession end");
			return new ResponseEntity<>(session, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	//get all active and inactive sessions
	@GetMapping(AppConstants.API_GET_ALL_SESSIONS)
	public ResponseEntity<List<Session>> getAllSessions() {
		logger.info("getAllSessions start");
		List<Session> sessions=sessionService.getAllSessions();
		if(sessions.isEmpty())
			throw new SessionNotFoundException();
		logger.info("getAllSessions end");
		return new ResponseEntity<>(sessions, HttpStatus.OK);
	}

	//get the session by id
	@GetMapping(AppConstants.API_GET_SESSION_BY_ID)
	public ResponseEntity<Session> getSessionById(@RequestParam String sessionId) {
		logger.info("getSessionById start");
		try
		{
			Session session=sessionService.getSessionById(sessionId);
			if(session == null)
				throw new SessionNotFoundException();
			logger.info("getSessionById end");
			return new ResponseEntity<>(session, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Exception raised when there is no session with the given sessionId",e.getMessage());
			throw new SessionNotFoundException();
		}
	}

	//get all sessions which are active
	@GetMapping(AppConstants.API_GET_ALL_ACTIVE_SESSIONS)
	public ResponseEntity<List<Session>> getAllActiveSessions() {
		logger.info("getAllActiveSessions start");
		List<Session> sessions=sessionService.getAllActiveSessions();
		if(sessions.isEmpty())
			throw new SessionNotFoundException();
		logger.info("getAllActiveSessions end");
		return new ResponseEntity<>(sessions, HttpStatus.OK);
	}


	// get title of the session
	@GetMapping(AppConstants.API_GET_TITLE_OF_THE_SESSION)
	public String getTitleOfTheSession(@RequestParam String sessionId) {
		logger.info("getTitleOfTheSession start");
		String title="";
		try
		{
			title=sessionService.getTitleOfTheSession(sessionId);
		}catch(EntityNotFoundException ex) {
			throw new SessionNotFoundException();
		}catch(Exception ex) {
			throw new SessionNotFoundException();
		}
		logger.info("getTitleOfTheSession end");
		return title;
	}

	//adding member to the session
	@PostMapping(AppConstants.API_ADD_MEMBER)
	public ResponseEntity<List<Member>> addUpdateMember(@Validated @RequestBody AddMemberRequest addMemReq) {
		logger.info("addUpdateMember start");
		try {
			List<Member> members = sessionService.addUpdateMember(addMemReq.getSessionId(),addMemReq.getMemberName());
			logger.info("addUpdateMember end");

			return new ResponseEntity<>(members, HttpStatus.OK);
		}catch(EntityNotFoundException ex) {
			throw new SessionNotFoundException();
		}catch(Exception ex) {
			throw new UpdateMemberException();
		}
	}

	//get list of userStories in session
	@GetMapping(AppConstants.API_GET_USERSTORY_LIST_IN_SESSION)
	public ResponseEntity<List<UserStory>> getUserStoryListInSession(@RequestParam String sessionId) {
		logger.info("getUserStoryListInSession start");
		try {
			List<UserStory> uStoryList= sessionService.getUserStoryListInSession(sessionId);

			if(uStoryList.isEmpty())
				throw new UserStoryNotFoundException();
			logger.info("getUserStoryListInSession end");
			return new ResponseEntity<>(uStoryList, HttpStatus.OK);
		}catch(EntityNotFoundException ex) {
			throw new SessionNotFoundException();
		}catch(Exception ex) {
			throw new UserStoryNotFoundException();
		}


	}

	//get list of members in session
	@GetMapping(AppConstants.API_GET_MEMBERS_IN_SESSION)
	public ResponseEntity<List<Member>> getMembersInSession(@RequestParam String sessionId) {
		logger.info("getMembersInSession start");
		try {
			List<Member> memberList= sessionService.getMembersInSession(sessionId);

			if(memberList.isEmpty())
				throw new MemberNotFoundException();
			logger.info("getMembersInSession end");
			return new ResponseEntity<>(memberList, HttpStatus.OK);
		}catch(EntityNotFoundException ex) {
			throw new SessionNotFoundException();
		}catch(Exception ex) {
			throw new MemberNotFoundException();
		}
	}
	
	//deleting the session and updating status as false
	@DeleteMapping(AppConstants.API_INACTIVE_SESSION)
	public void destroySession(@RequestParam String sessionId) {
		logger.info("destroySession start");
		try {
			sessionService.destroySession(sessionId);
		}catch(Exception ex) {
			throw new SessionNotFoundException();
		}

	}

}



