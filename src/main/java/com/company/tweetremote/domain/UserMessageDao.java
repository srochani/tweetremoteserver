package com.company.tweetremote.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Sunil on 13/02/2017.
 */
public interface UserMessageDao extends CrudRepository<UserMessage,Long> {

    List<UserMessage> findByUser(User user);
}
