package com.pokerrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "unable to add or update member")
public class UpdateMemberException extends RuntimeException {

	@Override
	public String toString()
	{
		return this.getClass().getName()+": unable to add and update member";
	}
}
