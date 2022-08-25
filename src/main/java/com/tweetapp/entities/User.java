package com.tweetapp.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Document("user")
@Data

public class User {

	@Id
	private String userId;
	@NotEmpty
	@NotBlank
	private String firstName;
	@NotEmpty
	@NotBlank
	private String lastName;
	@Indexed(unique = true)
	@Email
	@NotEmpty
	@NotBlank
	private String email;
	@Indexed(unique = true)
	@NotEmpty
	@NotBlank
	private String username;
	@NotEmpty
	@NotBlank
	private String password;
	
	@Pattern(regexp = "^[0-9]{10}",message = "contact Number should be 10 digit Number")
	private String contactNumber;
	
//	private List<Tweet> tweets; // One user can have multiple tweets
//
//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
//				+ ", username=" + username + ", password=" + password + ", contactNumber=" + contactNumber + ", tweets="
//				+ tweets + ", users=" + users + "]";
//	}
//
//	// private List<User> users;
	
	public User() {
		super();
	}
	
	public User(String firstName, String lastName, String email, String username, String password, String contactNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.contactNumber = contactNumber;
	}
}
