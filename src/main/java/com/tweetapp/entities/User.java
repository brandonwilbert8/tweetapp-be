package com.tweetapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Document("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
