package com.pokerrestapi.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokerrestapi.common.util.AppConstants;
import com.pokerrestapi.entity.Member;
import com.pokerrestapi.entity.MemberUserStory;
import com.pokerrestapi.entity.Session;
import com.pokerrestapi.entity.UserStory;
import com.pokerrestapi.entity.VotingStatus;
import com.pokerrestapi.exception.AlreadyVotedException;
import com.pokerrestapi.exception.SessionNotFoundException;
import com.pokerrestapi.repository.MemberRepository;
import com.pokerrestapi.repository.MemberUserStoryRepository;
import com.pokerrestapi.repository.SessionRepository;
import com.pokerrestapi.repository.UserStoryRepository;
import com.pokerrestapi.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {
	Logger logger = LoggerFactory.getLogger(VoteServiceImpl.class);

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	UserStoryRepository userStoryRepository;

	@Autowired
	MemberUserStoryRepository memberUserStoryRepository;

	@Override
	public UserStory startuserStoryVoting(String uStoryId) {
		logger.info("startuserStoryVoting start");
		UserStory userStory = userStoryRepository.getById(uStoryId);
		//after starting voting for userStory, status of userStory changes to voting from pending
		userStory.setVotingStatus(VotingStatus.VOTING);
		userStoryRepository.save(userStory);
		logger.info("startuserStoryVoting end");
		return userStory;
	}

	@Override
	public int submitUserStoryVote(String uStoryId,String memberId,String sessionId,Integer votePoint) {
		logger.info("submitUserStoryVote start");
		//checking the status of session whether it is active or not
		Session session= sessionRepository.getById(sessionId);
		if(session!=null && !session.isActive()) {
			throw new SessionNotFoundException();
		}
		UserStory uStory = userStoryRepository.getById(uStoryId);
		int count = uStory.getCount();
		Member member = memberRepository.getById(memberId);		
		String memberStatus = member.getMemberStatus();
		//if status of userStory is voted then raising the exception if we are trying to vote again
		if(uStory.getVotingStatus()!=null && uStory.getVotingStatus().compareTo(VotingStatus.VOTED)==0) {
			throw new AlreadyVotedException();
		}else if(uStory.getVotingStatus()!=null && uStory.getVotingStatus().compareTo(VotingStatus.PENDING)==0){
			uStory.setVotingStatus(VotingStatus.VOTING);
		}

		count = count+1;
		uStory.setCount(count);
		//after member voted the status of member changes to voted from not voted
		if(memberStatus != null && memberStatus.equalsIgnoreCase(AppConstants.NOT_VOTED)) {
			member.setMemberStatus(AppConstants.VOTED);
		}
		MemberUserStory memberUstory = new MemberUserStory();
		memberUstory.setMember(member);
		memberUstory.setUserStory(uStory);
		memberUstory.setVotePoint(votePoint);
		memberUserStoryRepository.save(memberUstory);
		logger.info("submitUserStoryVote end");
		return count;
	}

	//get all members with voting status
	@Override
	public List<Member> showMemberVotingStatus() {
		logger.info("showMemberVotingStatus start");
		List<Member> members = memberRepository.findAll();
		logger.info("showMemberVotingStatus end");
		return members;
	}

	// get all userStories with voting status
	@Override
	public List<UserStory> showUserStoryVotingStatus() {
		logger.info("showUserStoryVotingStatus start");
		List<UserStory> uStories = userStoryRepository.findAll();
		logger.info("showUserStoryVotingStatus end");
		return uStories;
	}


	@Override
	public List<MemberUserStory> stopuserStoryVoting(String uStoryId) {
		logger.info("stopuserStoryVoting start");
		UserStory userStory = userStoryRepository.getById(uStoryId); 
		//after stopping userStory voting, status of userStory changes to voted from voting
		userStory.setVotingStatus(VotingStatus.VOTED);
		userStoryRepository.save(userStory);
		List<MemberUserStory> memberUserStoryList = memberUserStoryRepository.findByUserStoryId(uStoryId);
		logger.info("stopuserStoryVoting end");
		return memberUserStoryList;
	}

	@Override
	public int getVoteCountForUserStory(String uStoryId) {
		logger.info("getVoteCountForUserStory start");
		UserStory userStory = userStoryRepository.getById(uStoryId); 
		int voteCount = userStory.getCount();
		logger.info("getVoteCountForUserStory end");
		return voteCount;
	}




}
