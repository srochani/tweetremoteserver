package com.company.tweetremote.service;

import com.company.tweetremote.domain.*;
import com.company.tweetremote.model.UserFollowDTO;
import com.company.tweetremote.model.UserMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;

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
    private UserFollowDao userFollowDao;

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

    /**
     *
     * Method to return messages for the user in reverse chronological order
     * @param userLogin
     * @return
     */
    @Transactional
    public List<UserMessage> getMessages(String userLogin) {
        List<UserMessage> userMessages = new ArrayList<>();
        List<User> userList = userDao.findByUserLogin(userLogin);
        if(userList != null && !userList.isEmpty()) {
            userMessages= userMessageDao.findByUserOrderByDateCreatedDesc(userList.get(0));
        }
        return userMessages;
    }


    /**
     * Method to set the following user
     * @param userFollowDto
     * @return
     */
    @Transactional
    public Boolean setFollowing(UserFollowDTO userFollowDto){
        Boolean isSet = false;
        // check if the users are exists
        User followerUser = userDao.findTop1ByUserLogin(userFollowDto.getFollowerUserLogin());
        User followingUser = userDao.findTop1ByUserLogin(userFollowDto.getFollowingUserLogin());
        if(followerUser != null && followerUser != null) {
            UserFollow userFollow = new UserFollow();
            userFollow.setFollowerId(followerUser.getUserId());
            userFollow.setFollowUser(followingUser);
            entityManager.persist(userFollow);
            isSet = true;
        }
        return isSet;
    }

    /**
     * Method to return timeline of the user with following user messages
     * @param userLogin
     * @return
     */
    @Transactional
    public List<UserMessage> getTimeLine(String userLogin) {
        List<UserMessage> userMessages = new ArrayList<>();
        User user = userDao.findTop1ByUserLogin(userLogin);
        if(user != null) {
            List<UserFollow> userFollowList = userFollowDao.findByFollowerId(user.getUserId());
            Set<User> followUserSet = new HashSet<>();
            if(userFollowList != null && !userFollowList.isEmpty()){
               userFollowList.forEach(uf -> {
                   followUserSet.add(uf.getFollowUser());
               });

               if(followUserSet != null && !followUserSet.isEmpty()){
                  userMessages = userMessageDao.findByUserOrderByDateCreatedDesc(followUserSet);

               }
            }
        }
        return userMessages;
    }


}
