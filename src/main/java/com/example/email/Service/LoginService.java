package com.example.email.Service;

import org.springframework.stereotype.Service;

import com.example.email.model.LoginModel;

@Service
public interface LoginService {
	LoginModel selectLoginByUsername(LoginModel loginModel);
}
