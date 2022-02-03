package com.pokerrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokerrestapi.common.util.AppConstants;
import com.pokerrestapi.dto.SubmitVoteRequest;
import com.pokerrestapi.entity.Member;
import com.pokerrestapi.entity.MemberUserStory;
import com.pokerrestapi.entity.UserStory;
import com.pokerrestapi.service.VoteService;

@RestController
@RequestMapping("/voting")
public class VoteController {


	@Autowired
	VoteService voteService;
	
	@PostMapping(AppConstants.API_START_USERSTORY_VOTING)
	public UserStory startuserStoryVoting(@RequestParam String uStoryId) {
		return voteService.startuserStoryVoting(uStoryId);
	}

	@PostMapping(AppConstants.API_SUBMIT_USERSTORY_VOTE)
	public int submitUserStoryVote( @Validated @RequestBody SubmitVoteRequest req ) {

		return voteService.submitUserStoryVote(req.getuStoryId(),req.getMemberId(),req.getSessionId(),req.getVotePoint());
	}

	@GetMapping(AppConstants.API_SHOW_MEMBER_VOTING_STATUS)
	public List<Member> showMemberVotingStatus() {

		return voteService.showMemberVotingStatus();
	}

	@GetMapping(AppConstants.API_SHOW_USERSTORY_VOTING_STATUS)
	public List<UserStory> showUserStoryVotingStatus() {

		return voteService.showUserStoryVotingStatus();
	}

	@PostMapping(AppConstants.API_STOP_USERSTORY_VOTING)
	public List<MemberUserStory> stopuserStoryVoting(@RequestParam String uStoryId) {
		return voteService.stopuserStoryVoting(uStoryId);
	}

	@GetMapping(AppConstants.API_GET_VOTE_COUNT_FOR_USERSTORY)
	public int getVoteCountForUserStory(@RequestParam String uStoryId) {
		return voteService.getVoteCountForUserStory(uStoryId);
	}

}
