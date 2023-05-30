package com.example.email.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.email.Service.RegisterService;
import com.example.email.impl.EmailImpl;
import com.example.email.jwt.TokenActive;
import com.example.email.model.EmailModel;
import com.example.email.model.RegisterModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EmailController {


    @Autowired
    private RegisterService registerService;
    @Autowired
    private TokenActive tokenActive;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private EmailImpl emailService;
    
    
    @GetMapping
    public String showSendEmailForm(Model model) {
        model.addAttribute("email", new EmailModel());
        return "sendmail";
    }
    @GetMapping("/register")
    public String getRegister() {
    	return "register";
    }
    
    
    @GetMapping("/login")
    public String getLogin() {
    	return "login";
    }
    // 新規するとき
    @PostMapping("/register")
    public String PostRegister ( @Validated RegisterModel registerModel,BindingResult result,Model model) throws MessagingException  {
  		log.info("{}", registerModel);
  		List<RegisterModel> checkusername = registerService.checkUsername(registerModel);
  		//ある個所が空になっている
        	if (result.hasErrors()) {
                List<String> errorList = new ArrayList<String>();
                for (ObjectError error : result.getAllErrors()) {
                    errorList.add(error.getDefaultMessage());
                    model.addAttribute("msg", errorList);
                }
                System.out.println(errorList);
            return "register";
//            }else if (checkusername != null) {
//            	 model.addAttribute("msg", "アカウントが存在しました");
//
//        		return "register";
        	}
        	else if (!registerModel.getPassword().matches("^[a-zA-Z0-9]+$")) {
        	    model.addAttribute("msg", "アルファベットと数字以外入力しないでください");
        	    return "register";
        	}else if (registerModel.getPassword().length() < 6) {
        		model.addAttribute("msg", "パスワードが６文字以上を入力してください");
        	    return "register";
        	}
        	else if (!registerModel.getPassword().equals(registerModel.getRepassword())) {
        		
        		model.addAttribute("msg", "パスワードと再入力が致しません");
        		return "register";
        	}
        	
        	else{
        		//tao encode pass
    		registerModel.setEncodePass(passwordEncoder.encode(registerModel.getPassword()));
    		System.out.println(registerModel.getEncodePass());
        		//tao ma keynumber 
        	String keynumber = tokenActive.creatKeyNumber();
        	registerModel.setKeynumber(keynumber);
        		//sendmail
        	emailService.RegisterEmail(registerModel);
            int insert =registerService.insert(registerModel);
            System.out.println(insert);
            if (insert == 1) {
            	model.addAttribute("msg", "aaaaa");
        		log.info("{}", registerModel);
        		return"redirect:/login";
                
            }else {

        		log.info("{}", registerModel);
                return "register";

            }
        	
        	
            
        }
    }
}
