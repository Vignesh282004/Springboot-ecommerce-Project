package com.project.project.service;

public interface MailServices {

	public void SendEmail(String name,String email,String subject,String message);
	
	
	
	
	public boolean SendEmail(String email,String subject,String message);
}
