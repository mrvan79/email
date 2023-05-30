
package com.example.email.model.ReturnValune;

import com.example.email.model.RegisterModel;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class ReturnValuneModel {
	private int code;
	private String status;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String information;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private RegisterModel data;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)//chi can null la khong xuat
	private String token;
	
}