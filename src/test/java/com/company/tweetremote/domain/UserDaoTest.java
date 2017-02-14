package com.company.tweetremote.domain;

import static org.assertj.core.api.Assertions.assertThat;

import javafx.application.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Sunil on 13/02/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDaoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserDao userDao;

    /**
     * Test findByUserLogin method to fetch User by user login
     */
    @Test
    public void testFindByUserLogin(){
        //User
        User user = new User("rob");
        entityManager.persist(user);

        //call method
        List<User> actualUsers = userDao.findByUserLogin(user.getUserLogin());

        //Assert
        assertThat(actualUsers).extracting(User::getUserLogin).contains(user.getUserLogin());


    }

    /**
     * Test findTop1ByUserLogin method to fetch User by user login
     */
    @Test
    public void testFindTop1ByUserLogin(){
        //User
        User user = new User("rob");
        entityManager.persist(user);

        //call method
        User actualUser = userDao.findTop1ByUserLogin(user.getUserLogin());

        //Assert
        Assert.assertNotNull(actualUser);
        Assert.assertEquals(user.getUserLogin(),actualUser.getUserLogin());


    }
}
