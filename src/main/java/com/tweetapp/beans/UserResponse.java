package com.tweetapp.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {


	boolean isSuccess;
	
	String errorMessage;
	
	public UserResponse(boolean isSuccess, String errorMessage) {
		this.isSuccess = isSuccess;
		this.errorMessage = errorMessage;
	}

}
