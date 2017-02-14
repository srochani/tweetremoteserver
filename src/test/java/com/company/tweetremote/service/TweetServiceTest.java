package com.company.tweetremote.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.tweetremote.domain.*;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Test
    public void getTimeLine(){
        String dummyMessage1 = "Test Message 1";
        String dummyMessage2 = "Test Message 2";

        User followerUser = new User("Alice");
        entityManager.persist(followerUser);

        //following User
        User user = new User("Bob");
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

        UserFollow userFollow = new UserFollow();
        userFollow.setFollowerId(followerUser.getUserId());
        userFollow.setFollowUser(user);
        entityManager.persist(userFollow);

        Set<User> users = new HashSet<>();
        users.add(user);

        // call method
        List<UserMessage> userMessageList = tweetService.getTimeLine(followerUser.getUserLogin());

        //assert
        assertThat(userMessageList).extracting(UserMessage::getMessage).contains(dummyMessage1);
        assertThat(userMessageList).extracting(UserMessage::getMessage).contains(dummyMessage2);

        Assert.assertEquals(dummyMessage2, userMessageList.get(0).getMessage());


    }


    }
