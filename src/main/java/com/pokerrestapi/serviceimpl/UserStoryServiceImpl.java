package com.pokerrestapi.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokerrestapi.common.util.AppConstants;
import com.pokerrestapi.entity.Session;
import com.pokerrestapi.entity.UserStory;
import com.pokerrestapi.entity.VotingStatus;
import com.pokerrestapi.exception.UserStoryDeletionNotPermitted;
import com.pokerrestapi.repository.SessionRepository;
import com.pokerrestapi.repository.UserStoryRepository;
import com.pokerrestapi.service.UserStoryService;

@Service
public class UserStoryServiceImpl implements UserStoryService {

	Logger logger = LoggerFactory.getLogger(UserStoryServiceImpl.class);
	
	@Autowired
	UserStoryRepository userStoryRepository;

	@Autowired
	SessionRepository sessionRepository;

	//adding userStory into session
	@Override
	public UserStory addUserStory(UserStory userStory,String sessionId) {
		logger.info("addUserStory start");
		Session session = sessionRepository.getById(sessionId);
		userStory.setCount(0);
		userStory.setVotingStatus(VotingStatus.PENDING);
		userStory.setSession(session);
		logger.info("addUserStory end");
		return userStoryRepository.save(userStory);
	}

	@Override
	public void deleteUserStory(String uStoryId) {
		logger.info("deleteUserStory start");
		//if voting status of userStory is pending then delete
		UserStory uStory = userStoryRepository.getById(uStoryId);
		if(uStory.getVotingStatus() != null && 
				uStory.getVotingStatus().toString().equals(AppConstants.PENDING)) 
		{
			userStoryRepository.deleteById(uStoryId);
		}else {
			logger.error("Exception raised while deleting the userStory if status is not pending");
			//if voting status of userStory is voting or voted then we can't delete the userStory throwing exception
			throw new UserStoryDeletionNotPermitted();
		}
		logger.info("deleteUserStory end");
	}
	
	//get all userStories
	@Override
	public List<UserStory> getAllUserStories() {
		logger.info("getAllUserStories start");
		List<UserStory> userStories = userStoryRepository.findAll();
		logger.info("getAllUserStories end");
		return userStories;
	}
	
	//get userStory by id
	@Override
	public UserStory getUserStory(String uStoryId) {
		logger.info("getUserStory start");
		UserStory uStory = userStoryRepository.getUserStory(uStoryId);
		logger.info("getUserStory end");
		return uStory;
	}

}
