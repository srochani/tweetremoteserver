package com.company.tweetremote.service;

import com.company.tweetremote.domain.User;
import com.company.tweetremote.domain.UserDao;
import com.company.tweetremote.domain.UserMessage;
import com.company.tweetremote.domain.UserMessageDao;
import com.company.tweetremote.model.UserMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

/**
 * Service class for
 * Created by Sunil on 14/02/2017.
 */
@Component
public class TweetService {

    @Autowired
    private UserMessageDao userMessageDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EntityManager entityManager;

    /**
     * Method to post messages, if user is not present then it will create it
     * @param userMessageDTO
     * @return
     */
    @Transactional
    public UserMessage postMessage(UserMessageDTO userMessageDTO){
        UserMessage userMessage = new UserMessage();
        // check if the user exits
        List<User> userList = userDao.findByUserLogin(userMessageDTO.getUserLogin());
        // if user is not exists then create it
        if(userList == null || userList.isEmpty()){
            // create user
            User user = new User(userMessageDTO.getUserLogin());
            entityManager.persist(user);
            userMessage.setUser(user);
        }
        else{
            userMessage.setUser(userList.get(0));
        }

        userMessage.setMessage(userMessageDTO.getMessage());
        userMessage.setDateCreated(new Date());

        entityManager.persist(userMessage);

        return userMessage;
    }
}
