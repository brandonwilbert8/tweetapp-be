package com.tweetapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("reply")
public class Reply {
    @Id
    private Integer replyTweetId;
    private Integer tweetId;
    @Max(value = 144)
    @NotBlank(message = "reply tweet Cannot be Blank. please enter tweet")
    @Pattern(regexp = "^[a-zA-Z0-9]")
    private String replyTweet;
    private String username;
    private Like like;
    private List<Reply> replies;
    private String tag;
//    @Temporal(TemporalType.TIMESTAMP)
//	private Date createTweetDate;
}
