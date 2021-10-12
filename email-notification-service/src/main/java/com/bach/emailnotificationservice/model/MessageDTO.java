package com.bach.emailnotificationservice.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;

import lombok.Data;

@Data
public class MessageDTO {

	@Email
	private String sendTo;
	
	@Max(value = 100)
	private String subject;
	
	@Max(value = 500)
	private String body;
	
}
