package com.example.email.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.email.Service.LoginService;
import com.example.email.mapper.LoginMapper;
import com.example.email.model.LoginModel;

@Service
public class LoginImpl implements LoginService{
	@Autowired
	private LoginMapper mapper;

	@Override
	public LoginModel selectLoginByUsername(LoginModel loginModel) {
		return mapper.selectLoginByUsername(loginModel);
	}
	

}
