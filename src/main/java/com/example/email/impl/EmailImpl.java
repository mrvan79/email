package com.example.email.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.email.Service.EmailService;
import com.example.email.model.RegisterModel;

@Service
public class EmailImpl implements EmailService {
//	@Autowired
//	private EmailMapper emailMapper;
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private TemplateEngine templateEngine;

	public void RegisterEmail(RegisterModel registerModel) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(registerModel.getEmail());
		helper.setSubject("新規登録メール!");
		Context context = new Context();
		context.setVariable("username", registerModel.getUsername());
		context.setVariable("password", registerModel.getPassword());
		context.setVariable("tokenKey", registerModel.getKeynumber());
		String htmlContent = templateEngine.process("mail", context);
		helper.setText(htmlContent, true);
		emailSender.send(message);
	}
	
	public void sendEmail (String email, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("nguyenminhvan1998dn@gmail.com");
		message.setTo(email);
		message.setText(body);
		message.setSubject(subject);
		
		emailSender.send(message);
		
		System.out.println("sendmail success");
		
	}

}
