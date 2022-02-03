package com.pokerrestapi.service;

import java.util.List;

import com.pokerrestapi.entity.UserStory;
public interface UserStoryService {
	public UserStory addUserStory(UserStory userStory,String sessionId);
	
	public void deleteUserStory(String uStoryId);
	
	public List<UserStory> getAllUserStories();
	
	public UserStory getUserStory(String uStoryId);
}
