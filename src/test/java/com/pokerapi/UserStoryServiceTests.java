package com.pokerapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.pokerrestapi.constant.PlanningPokerConstant;
import com.pokerrestapi.entity.Member;
import com.pokerrestapi.entity.Session;
import com.pokerrestapi.entity.UserStory;
import com.pokerrestapi.entity.VotingStatus;
import com.pokerrestapi.repository.SessionRepository;
import com.pokerrestapi.repository.UserStoryRepository;
import com.pokerrestapi.serviceimpl.UserStoryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class UserStoryServiceTests {

	@InjectMocks
	public UserStoryServiceImpl userStoryServiceMocked;

	@Mock
	SessionRepository pokerSessionRepository;

	@Mock
	UserStoryRepository userStoryRepository;
	
	 static List<Session> pokerSessions = new ArrayList<Session>();
	 static Session pokerSession = null;
	 static Member member2 = null;
	 static UserStory userStory2 = null;
	
	

	@Test
	void addUserStoryTest() {

		String session = "12345";
		Mockito.when(pokerSessionRepository.getById(session)).thenReturn(pokerSession);
		Mockito.when(userStoryRepository.save(userStory2)).thenReturn(userStory2);
		UserStory us = userStoryServiceMocked.addUserStory(userStory2, session);
		assertEquals(userStory2, us);
	}

	@Test
	void getUserStoryTest() {

		String uStoryId = "us2";
		Mockito.when(userStoryRepository.getById(uStoryId)).thenReturn(userStory2);
		UserStory us = userStoryServiceMocked.getUserStory(uStoryId);
		assertEquals(userStory2, us);
	}
	
	@Test
	void deleteUserStoryTest() {

		String uStoryId = "us2";
		Mockito.when(userStoryRepository.getById(uStoryId)).thenReturn(userStory2);
		userStoryServiceMocked.deleteUserStory(uStoryId);
		System.out.println("done!!!");
	}
	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		pokerSession = createSession();
		pokerSessions = createSessionList(pokerSession);
		userStory2 = createUserStory();
	}

	
	
	private UserStory createUserStory() {
		userStory2 = new UserStory("us2", "desc2", 0);
		userStory2.setVotingStatus(VotingStatus.PENDING);
		pokerSession.getUserStories().add(userStory2);
		userStory2.setSession(pokerSession);
		return userStory2;
	}



	public static List<Session> createSessionList(Session pokerSession) {
		pokerSessions = new ArrayList<Session>();
		
		
		List<Session> pokerSessions = new ArrayList<Session>();
		pokerSessions.add(pokerSession);
		return pokerSessions;
	}

	private Session createSession() {
		UserStory userStory = new UserStory("us1", "desc1", 0);
		userStory.setVotingStatus(VotingStatus.PENDING);
		Set<UserStory> userStories = new HashSet<UserStory>();
		userStories.add(userStory);
		Member member = new Member("XYZ", PlanningPokerConstant.NOT_VOTED);
		member.setMemberId("1");
		Set<Member> members = new HashSet<Member>();
		members.add(member);
		pokerSession = new Session("12345", "title1", "deck1", userStories, members);
		return pokerSession;
	}
}
