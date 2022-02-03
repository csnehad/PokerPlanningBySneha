package com.pokerrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "userstory not found")
public class UserStoryNotFoundException extends RuntimeException{

	@Override
	public String toString()
	{
		return this.getClass().getName()+": UserStory not found";
	}
}
