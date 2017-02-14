package com.company.tweetremote.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Sunil on 13/02/2017.
 */
public interface UserFollowDao extends CrudRepository<UserFollow,Long> {

    List<UserFollow> findByFollowerId(Long userId);

}
