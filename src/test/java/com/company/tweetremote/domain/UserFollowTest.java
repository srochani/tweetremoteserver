package com.company.tweetremote.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Sunil on 13/02/2017.
 * Test class for UserFollow
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserFollowTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserFollowDao userFollowDao;

    /**
     * Test method findByUser which will return user following
     */
    @Test
    public void findByUser(){
        //User
        User user = new User("Alice");
        entityManager.persist(user);

        //Follow user
        User followUser = new User("Bob");
        entityManager.persist(followUser);
        List<User> followUsers = new ArrayList<>();
        followUsers.add(followUser);

        // User to user follow relation
        UserFollow userFollow = new UserFollow();
        userFollow.setFollowerId(user.getUserId());
        userFollow.setFollowUser(followUser);
        entityManager.persist(userFollow);

        //call method
        List<UserFollow> actualFollowUsers = userFollowDao.findByFollowerId(user.getUserId());

        assertThat(actualFollowUsers).extracting(UserFollow::getUserFollowId).containsOnly(user.getUserId());

        assertThat(actualFollowUsers).containsExactly(userFollow);

        assertThat(actualFollowUsers).extracting(UserFollow::getFollowUser)
                .extracting(User::getUserLogin).containsExactly(followUser.getUserLogin());

    }
}
