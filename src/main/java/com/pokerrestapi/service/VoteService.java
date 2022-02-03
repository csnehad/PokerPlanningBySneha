package com.pokerrestapi.service;

import java.util.List;

import com.pokerrestapi.entity.Member;
import com.pokerrestapi.entity.MemberUserStory;
import com.pokerrestapi.entity.UserStory;

public interface VoteService {

	int submitUserStoryVote(String uStoryId,String memberId,String sessionId, Integer votePoint);
	
	List<Member> showMemberVotingStatus();
	
	List<UserStory> showUserStoryVotingStatus();
	
	UserStory startuserStoryVoting(String uStoryId);
	
	List<MemberUserStory> stopuserStoryVoting(String uStoryId);
	
	int getVoteCountForUserStory(String uStoryId);
}
