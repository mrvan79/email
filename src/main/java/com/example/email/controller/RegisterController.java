package com.example.email.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.email.Service.RegisterService;
import com.example.email.impl.EmailImpl;
import com.example.email.jwt.TokenActive;
import com.example.email.model.RegisterModel;
import com.example.email.model.ReturnValune.ReturnValuneModel;

import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/")
public class RegisterController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private TokenActive tokenActive;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private EmailImpl emailService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegisterModel registerModel,ReturnValuneModel returnValuneModel,BindingResult result)throws MessagingException {
		log.info("{}", registerModel);
    	if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            System.out.println(errorList);
        return ResponseEntity.ok(errorList);
    	}else {
    		//tao encode pass
		registerModel.setEncodePass(passwordEncoder.encode(registerModel.getPassword()));
		System.out.println(registerModel.getEncodePass());
    		//tao ma keynumber 
    	String keynumber = tokenActive.creatKeyNumber();
    	registerModel.setKeynumber(keynumber);
        int insert =registerService.insert(registerModel);
        System.out.println(insert);
        if (insert == 1) {
        	returnValuneModel.setCode(200);
        	returnValuneModel.setStatus("SUCCSESS");
        	returnValuneModel.setData(registerModel);
        	emailService.RegisterEmail(registerModel);
    		log.info("{}", returnValuneModel);
            return ResponseEntity.status(200).body(returnValuneModel);
            
        }else {
        	returnValuneModel.setCode(400);
        	returnValuneModel.setStatus("default");
        	returnValuneModel.getToken();
    		log.info("{}", returnValuneModel);
            return ResponseEntity.status(400).body(registerModel);

        }
    	}
    	
        
    }
}
