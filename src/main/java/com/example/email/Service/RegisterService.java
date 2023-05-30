package com.example.email.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.email.model.RegisterModel;

@Service
public interface RegisterService {
	int insert (RegisterModel registerModel);
	List<RegisterModel> checkUsername (RegisterModel registerModel);
}
