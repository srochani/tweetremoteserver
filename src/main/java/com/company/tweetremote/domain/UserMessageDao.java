package com.company.tweetremote.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by Sunil on 13/02/2017.
 */
public interface UserMessageDao extends CrudRepository<UserMessage,Long> {

    List<UserMessage> findByUser(User user);

    List<UserMessage> findByUserOrderByDateCreatedDesc(User user);

    List<UserMessage> findByUserOrderByDateCreatedDesc(Set<User> user);

}
