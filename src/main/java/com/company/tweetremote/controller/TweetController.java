package com.company.tweetremote.controller;

import com.company.tweetremote.domain.UserMessage;
import com.company.tweetremote.model.UserFollowDTO;
import com.company.tweetremote.model.UserMessageDTO;
import com.company.tweetremote.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Sunil on 14/02/2017.
 */
@Controller
@RequestMapping("/tweetremoteserver")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    /**
     * End point to post message
     * @param userMessage
     * @return
     */
    @RequestMapping(value="/postmessage", method = RequestMethod.POST)
    public @ResponseBody
    UserMessage postMessage(@RequestBody UserMessageDTO userMessage ){
        return tweetService.postMessage(userMessage);
    }

    /**
     * End point to get messages in reverse chronological order
     * @param userLogin
     * @return
     */
    @RequestMapping(value="/getmessages/{userLogin}", method = RequestMethod.GET)
    public @ResponseBody
    List<UserMessage> getMessages(@RequestParam(value="userLogin") String userLogin){
        return tweetService.getMessages(userLogin);
    }

    @RequestMapping(value="/following", method = RequestMethod.POST)
    public @ResponseBody
    Boolean setFollowing(@RequestBody UserFollowDTO userFollowDto){
        return tweetService.setFollowing(userFollowDto);
    }


}
