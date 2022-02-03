package com.pokerrestapi.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "session not found")
public class SessionNotFoundException extends RuntimeException {

	@Override
	public String toString()
	{
		return this.getClass().getName()+": Session not found";
	}
}