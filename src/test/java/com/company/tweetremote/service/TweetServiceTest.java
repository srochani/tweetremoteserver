package com.company.tweetremote.service;

import static org.assertj.core.api.Assertions.assertThat;
import com.company.tweetremote.domain.UserDao;
import com.company.tweetremote.domain.UserMessage;
import com.company.tweetremote.domain.UserMessageDao;
import com.company.tweetremote.model.UserMessageDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TweetService test class
 * Created by Sunil on 14/02/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TweetServiceTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserMessageDao userMessageDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TweetService tweetService;

    @Test
    public void postMessage(){
        UserMessageDTO userMessageDTO = new UserMessageDTO();
        userMessageDTO.setUserLogin("TEST_USER");
        userMessageDTO.setMessage("TEST_MESSAGE");

        //actual call
        UserMessage actualUserMessage = tweetService.postMessage(userMessageDTO);

        Assert.assertEquals(actualUserMessage.getMessage(),userMessageDTO.getMessage());
        Assert.assertEquals(actualUserMessage.getUser().getUserLogin(),userMessageDTO.getUserLogin());

    }

}
