package com.pokerapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
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
import com.pokerrestapi.entity.UserStory;
import com.pokerrestapi.entity.VotingStatus;
import com.pokerrestapi.repository.MemberRepository;
import com.pokerrestapi.repository.UserStoryRepository;
import com.pokerrestapi.serviceimpl.VoteServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class VoteServiceTests {

	@InjectMocks
	public VoteServiceImpl voteServiceMocked;

	@Mock
	MemberRepository memberRepository;

	@Mock
	UserStoryRepository userStoryRepository;

	static Member member2 = null;
	static UserStory userStory2 = null;



	@Test
	void submitUserStoryVoteTest() {

		String uStoryId = "us1";
		String memberId = "1";
		String session = "12345";
		Integer voteCount = 4;
		Mockito.when(userStoryRepository.save(userStory2)).thenReturn(userStory2);
		Mockito.when(userStoryRepository.getById(uStoryId)).thenReturn(userStory2);
		Mockito.when(memberRepository.getById(memberId)).thenReturn(member2);
		int count = voteServiceMocked.submitUserStoryVote(uStoryId, memberId, session,voteCount);
		assertEquals(1, count);
	}



	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		member2 = createMember();
		userStory2 = createUserStory();

	}
	static Set<Member> members = null;
	private Member createMember() {
		member2 = new Member("ABC", PlanningPokerConstant.VOTED);
		member2.setMemberId("2");
		members = new HashSet<Member>();
		members.add(member2);
		return member2;
	}


	private UserStory createUserStory() {
		userStory2 = new UserStory("us2", "desc2", 0);
		userStory2.setVotingStatus(VotingStatus.PENDING);
		Set<UserStory> userStories = new HashSet<UserStory>();
		userStories.add(userStory2);
		return userStory2;
	}




}
