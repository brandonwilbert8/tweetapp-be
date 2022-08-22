package com.tweetapp.services;

import com.tweetapp.dao.ReplyRepository;
import com.tweetapp.entities.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    @Autowired
    ReplyRepository replyRepository;

    public Reply getReplyById(Integer replyId) {
        return replyRepository.findById(replyId).get();
    }
    public Reply save(Reply save) {
        return replyRepository.save(save);
    }
}
