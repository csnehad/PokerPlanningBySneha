package com.pokerrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "already voted")
public class AlreadyVotedException extends RuntimeException {

	@Override
	public String toString()
	{
		return this.getClass().getName()+":UserStory already voted ";
	}
}
