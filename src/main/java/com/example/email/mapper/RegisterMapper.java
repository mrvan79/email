package com.example.email.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.email.model.RegisterModel;

@Mapper
public interface RegisterMapper {
	int insert (RegisterModel registerModel);
	List<RegisterModel> checkUsername (RegisterModel registerModel);

}