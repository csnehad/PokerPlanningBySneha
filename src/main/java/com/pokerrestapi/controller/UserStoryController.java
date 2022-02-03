package com.pokerrestapi.controller;

import java.util.List;

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
import com.pokerrestapi.dto.AddUserStoryRequest;
import com.pokerrestapi.entity.UserStory;
import com.pokerrestapi.exception.UserStoryDeletionNotPermitted;
import com.pokerrestapi.exception.UserStoryNotFoundException;
import com.pokerrestapi.service.UserStoryService;

@RestController
@RequestMapping("/userstory")
public class UserStoryController {
	Logger logger = LoggerFactory.getLogger(UserStoryController.class);

	@Autowired
	UserStoryService userStoryService;

	//adding userStory to session
	@PostMapping(AppConstants.API_ADD_USERSTORY)
	public ResponseEntity<UserStory> addUserStory(@Validated @RequestBody AddUserStoryRequest uStoryReq) {
		logger.info("addUserStory start");
		try {
			UserStory userStory = new UserStory(uStoryReq.getSessionId(), uStoryReq.getDescription(), 0);
			UserStory uStory = userStoryService.addUserStory(userStory,uStoryReq.getSessionId());
			logger.info("addUserStory end");
			return new ResponseEntity<>(uStory, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//deleting userStory if the status is pending
	@DeleteMapping(AppConstants.API_DELETE_USERSTORY)
	public void deleteUserStory(@RequestParam String uStoryId) {
		logger.info("deleteUserStory start");
		try {
			userStoryService.deleteUserStory(uStoryId);
		} catch (Exception e) {
			logger.error("Exception raised while deleting the userStory if status is not pending",e.getMessage());
			throw new UserStoryDeletionNotPermitted();
		}
	}

	//get usrStory by id
	@GetMapping(AppConstants.API_GET_USERSTORY)
	public ResponseEntity<UserStory> getUserStory(@RequestParam String uStoryId) {
		logger.info("getUserStory start");
		try {
			UserStory uStory = userStoryService.getUserStory(uStoryId);
			if(uStory == null)
				throw new UserStoryNotFoundException();
			logger.info("getUserStory end");
			return  new ResponseEntity<>(uStory, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception raised while getting the userStory if there is no userStory with the given uStoryId ",e.getMessage());
			throw new UserStoryNotFoundException();
		}

	}

	//get all userStories
	@GetMapping(AppConstants.API_GET_ALL_USERSTORIES)
	public ResponseEntity<List<UserStory>> getAllUserStories() {
		logger.info("getAllUserStories start");
		try {
			List<UserStory> uStories = userStoryService.getAllUserStories();
			if (uStories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			logger.info("getAllUserStories end");
			return new ResponseEntity<>(uStories, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception raised while getting the list of userStories if there is no userStory list ",e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
