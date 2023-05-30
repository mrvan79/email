package com.example.email.jwt;

import java.time.Instant;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class TokenActive {
	@Value("${myapp.timeKeyNumber}")
	private int timeKeyNumber;
//	tạo keynumber với từ đầu tiên là A là active user và R là lấy lại MK
	public String creatKeyNumber() {
		String keyNumber ="";
		for (int i=0;i<6;i++) {
			keyNumber+=new Random().nextInt(10);
		}
		keyNumber+="."+Instant.now();
		return keyNumber;
	}
	public Boolean checkTimeToken(String timeToken) {
	    Instant now = Instant.now();
	    Instant t500s = Instant.parse(timeToken.substring(timeToken.indexOf(".") + 1));
	    long diffInSeconds = now.getEpochSecond() - t500s.getEpochSecond();
	    System.out.println(diffInSeconds);
	    return diffInSeconds>timeKeyNumber;
	}
}
