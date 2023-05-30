package com.example.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.email.Service.LoginService;
import com.example.email.model.LoginModel;
import com.example.email.model.ReturnValune.ReturnValuneModel;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class LoginController {
	@Autowired 
	LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<?> Postlogin (@RequestBody LoginModel loginModel,ReturnValuneModel returnValuneModel)throws AuthenticationException{
		log.info("{}", loginModel);
		

			BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
			LoginModel loginuser = loginService.selectLoginByUsername(loginModel);
			if (!bCrypt.matches(loginModel.getPassword(),loginuser.getPassword())) {
				returnValuneModel.setCode(400);
				returnValuneModel.setStatus("Default");
				returnValuneModel.setInformation("");
				return ResponseEntity.status(400).body(returnValuneModel);}
			else if (!loginuser.equals(null)) {
				returnValuneModel.setCode(200);
				returnValuneModel.setStatus("SUCCSESS");
				log.info("{}", returnValuneModel);
				return ResponseEntity.ok(returnValuneModel);			
			}else {
				returnValuneModel.setCode(400);
				returnValuneModel.setStatus("Default");
				return ResponseEntity.status(400).body(returnValuneModel);
			}
			
			

		
		
	}
}
