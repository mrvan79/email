package com.example.email.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.email.model.LoginModel;

@Repository
@Mapper
public interface LoginMapper {
	LoginModel selectLoginByUsername(LoginModel loginModel);

}
