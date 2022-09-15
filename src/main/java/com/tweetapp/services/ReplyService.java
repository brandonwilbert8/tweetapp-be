package com.tweetapp.services;

import com.tweetapp.dao.ReplyRepository;
import com.tweetapp.entities.Reply;
import com.tweetapp.exception.ReplyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    @Autowired
    ReplyRepository replyRepository;

    public Reply getReplyById(String replyId) {
        return replyRepository.findById(replyId).orElseThrow(() -> new ReplyNotFoundException("Cannot identify the tweet/reply"));
    }
    public Reply save(Reply save) {
        return replyRepository.save(save);
    }
}
