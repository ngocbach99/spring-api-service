package com.bach.emailnotificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bach.emailnotificationservice.model.MessageDTO;
import com.bach.emailnotificationservice.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/notification")
@Slf4j
public class NotificationController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping(value="/textemail",consumes = "application/json", produces = "application/json")
	public String sendEmail(@RequestBody MessageDTO messageDTO) {
		try {
			log.info("Sending Simple Text Email....");
			emailService.sendTextEmail(messageDTO);
			return "Email Sent!";
		} catch (Exception ex) {
			return "Error in sending email: " + ex;
		}
	}
	
	
	@PostMapping(value="/attachemail",consumes = "multipart/form-data")
	public String sendEmailWithAttachment(@RequestPart(value = "file") MultipartFile file) {
		try {
			log.info("Sending Attachment Email....");
			emailService.sendEmailWithAttachment(file);
			return "Email Sent!";
		} catch (Exception ex) {
			return "Error in sending email: " + ex;
		}
	}

	
}
