package com.tweetapp.entities;

import java.util.List;

public class Like {
	
	private Integer noOfLikes;
	private List<String> details;
	public int getNoOfLikes() {
		return noOfLikes;
	}
	public void setNoOfLikes(Integer noOfLikes) {
		this.noOfLikes = noOfLikes;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}

}
