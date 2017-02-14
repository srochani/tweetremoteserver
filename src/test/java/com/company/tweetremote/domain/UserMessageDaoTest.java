package com.company.tweetremote.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Sunil on 13/02/2017.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserMessageDaoTest {
    //Test entityManager for persistent

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserMessageDao userMessageDao;

    /**
     * Test findByUser method to find the messages based on the user
     */
    @Test
    public void findByUser(){
        String dummyMessage = "Test Message";
        //User
        User user = new User("rob");
        entityManager.persist(user);
        Date dateCreated = new Date();

        //UserMessage
        UserMessage userMessage = new UserMessage();
        userMessage.setMessage(dummyMessage);
        userMessage.setDateCreated(dateCreated);
        userMessage.setUser(user);
        entityManager.persist(userMessage);

        // call method
        List<UserMessage> userMessageList = userMessageDao.findByUser(user);
        System.out.println(dateCreated);

        //assert
        assertThat(userMessageList).extracting(UserMessage::getMessage).contains(dummyMessage);


    }

    /**
     * Test findByUser method to find the messages based on the user in reverse chronological
     */
    @Test
    public void findByUserOrderByDateCreatedDesc(){
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

        // call method
        List<UserMessage> userMessageList = userMessageDao.findByUserOrderByDateCreatedDesc(user);

        //assert
        assertThat(userMessageList).extracting(UserMessage::getMessage).contains(dummyMessage1);
        assertThat(userMessageList).extracting(UserMessage::getMessage).contains(dummyMessage2);

        Assert.assertEquals(dummyMessage2, userMessageList.get(0).getMessage());

    }

    @Test
    public void findByUserOrderByDateCreatedDescMultipleUsers(){

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
        List<UserMessage> userMessageList = userMessageDao.findByUserOrderByDateCreatedDesc(users);

        //assert
        assertThat(userMessageList).extracting(UserMessage::getMessage).contains(dummyMessage1);
        assertThat(userMessageList).extracting(UserMessage::getMessage).contains(dummyMessage2);

        Assert.assertEquals(dummyMessage2, userMessageList.get(0).getMessage());

    }
}
