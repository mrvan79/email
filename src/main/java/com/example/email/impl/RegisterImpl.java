package com.example.email.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.email.Service.RegisterService;
import com.example.email.mapper.RegisterMapper;
import com.example.email.model.RegisterModel;

@Service
public class RegisterImpl implements RegisterService {
	@Autowired
	private RegisterMapper mapper;

	@Override
	public int insert(RegisterModel registerModel) {
		return mapper.insert(registerModel);
	}

	@Override
	public List<RegisterModel> checkUsername(RegisterModel registerModel) {
		return mapper.checkUsername(registerModel);
	}

	



	

	
	
}
