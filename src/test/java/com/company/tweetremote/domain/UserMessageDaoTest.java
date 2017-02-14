package com.company.tweetremote.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

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

        //assert
        assertThat(userMessageList).extracting(UserMessage::getMessage).contains(dummyMessage);


    }
}
