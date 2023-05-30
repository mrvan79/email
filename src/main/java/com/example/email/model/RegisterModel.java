package com.example.email.model;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class RegisterModel {
	@NotEmpty(message = "ユーザー名を入力してください")
	private String username;
	@NotEmpty(message = "パスワードを入力してください")
	private String password;
	@NotEmpty(message = "再パスワードを入力してください")
	private String repassword;
	@NotEmpty(message = "メールを入力してください")
	private String email;
	@JsonIgnore
	private String roles;
	@JsonIgnore
	private String status;
	@JsonIgnore
	private String encodePass;
	private String keynumber;

}
