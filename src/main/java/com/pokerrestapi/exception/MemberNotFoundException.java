package com.pokerrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "member not found")
public class MemberNotFoundException extends RuntimeException {

	@Override
	public String toString()
	{
		return this.getClass().getName()+": Member not found";
	}
}
