package com.company.tweetremote.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.tweetremote.domain.User;
import com.company.tweetremote.domain.UserDao;
import com.company.tweetremote.domain.UserMessage;
import com.company.tweetremote.domain.UserMessageDao;
import com.company.tweetremote.model.UserFollowDTO;
import com.company.tweetremote.model.UserMessageDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

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

    @Test
    public void getMessages(){
        String dummyMessage1 = "Test Message 1";
        String dummyMessage2 = "Test Message 2";
        //User
        User user = new User("rob");
        entityManager.persist(user);
        Date dateCreated = new Date();
        //UserMessage1
        UserMessage userMessage1 = new UserMessage();
        userMessage1.setMessage(dummyMessage1);
        userMessage1.setDateCreated(dateCreated);
        userMessage1.setUser(user);
        entityManager.persist(userMessage1);

        dateCreated = new Date();
        dateCreated.setTime(dateCreated.getTime() + 100);

        //UserMessage2
        UserMessage userMessage2 = new UserMessage();
        userMessage2.setMessage(dummyMessage2);
        userMessage2.setDateCreated(dateCreated);
        userMessage2.setUser(user);
        entityManager.persist(userMessage2);

        //actual call
        List<UserMessage> actualUserMessages = tweetService.getMessages(user.getUserLogin());

        assertThat(actualUserMessages).extracting(UserMessage::getMessage).contains(dummyMessage1);
        assertThat(actualUserMessages).extracting(UserMessage::getMessage).contains(dummyMessage2);

        Assert.assertEquals(dummyMessage2, actualUserMessages.get(0).getMessage());


    }

    @Test
    public void setFollowing(){
        //User
        User user1 = new User("Alice");
        entityManager.persist(user1);

        //User
        User user2 = new User("Bob");
        entityManager.persist(user2);

        UserFollowDTO userFollowDTO = new UserFollowDTO();
        userFollowDTO.setFollowerUserLogin(user1.getUserLogin());
        userFollowDTO.setFollowingUserLogin(user2.getUserLogin());

        Boolean actucalResult = tweetService.setFollowing(userFollowDTO);

        Assert.assertTrue(actucalResult);

    }
}
