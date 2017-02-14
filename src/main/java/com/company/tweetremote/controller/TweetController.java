package com.company.tweetremote.controller;

import com.company.tweetremote.domain.UserMessage;
import com.company.tweetremote.model.UserMessageDTO;
import com.company.tweetremote.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Sunil on 14/02/2017.
 */
@Controller
@RequestMapping("/tweetremoteserver")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @RequestMapping(value="/postmessage", method = RequestMethod.POST)
    public @ResponseBody
    UserMessage postMessage(@RequestBody UserMessageDTO userMessage ){
        return tweetService.postMessage(userMessage);
    }
}
