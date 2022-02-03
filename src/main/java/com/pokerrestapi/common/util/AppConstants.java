package com.pokerrestapi.common.util;

public final class AppConstants {

	private AppConstants() {
		super();
	}
	public static final String API_CREATE_SESSION = "/createSession";
	public static final String API_GET_ALL_SESSIONS = "/getAllSessions";
	public static final String API_GET_SESSION_BY_ID = "/getSessionById";                       
	public static final String API_GET_ALL_ACTIVE_SESSIONS = "/getAllActiveSessions";
	public static final String API_GET_TITLE_OF_THE_SESSION = "/getTitleOfTheSession";
	public static final String API_ADD_MEMBER = "/addMember";
	public static final String API_GET_USERSTORY_LIST_IN_SESSION = "/getUserStoryListInSession";
	public static final String API_GET_MEMBERS_IN_SESSION = "/getMembersInSession";
	public static final String API_INACTIVE_SESSION = "/inactiveSession";
	
	public static final String API_ADD_USERSTORY = "/addUserStory";
	public static final String API_DELETE_USERSTORY = "/deleteUserStory";
	public static final String API_GET_USERSTORY = "/getUserStory";
	public static final String API_GET_ALL_USERSTORIES = "/getAllUserStories";
	
	public static final String API_START_USERSTORY_VOTING = "/startuserStoryVoting";
	public static final String API_SUBMIT_USERSTORY_VOTE = "/submitUserStoryVote";
	public static final String API_SHOW_MEMBER_VOTING_STATUS = "/showMemberVotingStatus";
	public static final String API_SHOW_USERSTORY_VOTING_STATUS = "/showUserStoryVotingStatus";
	public static final String API_STOP_USERSTORY_VOTING = "/stopuserStoryVoting";
	public static final String API_GET_VOTE_COUNT_FOR_USERSTORY = "/getVoteCountForUserStory";

	public static final String PENDING = "PENDING";
	public static final String VOTED = "VOTED";
	public static final String VOTING = "VOTING";
	public static final String NOT_VOTED = "NOT_VOTED";
	public static final int DEFAULT_VOTE_COUNT = 0;

}
