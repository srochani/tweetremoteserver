package com.company.tweetremote.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Sunil on 13/02/2017.
 * Entity to hold information of user and following user
 */
@Entity
public class UserFollow {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userFollowId;

    private Long followerId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User followUser;

    public Long getUserFollowId() {
        return userFollowId;
    }

    public void setUserFollowId(Long userFollowId) {
        this.userFollowId = userFollowId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public User getFollowUser() {
        return followUser;
    }

    public void setFollowUser(User followUser) {
        this.followUser = followUser;
    }

    @Override
    public String toString() {
        return "UserFollow{" +
                "userFollowId=" + userFollowId +
                ", followerId=" + followerId +
                ", followUser=" + followUser +
                '}';
    }
}
