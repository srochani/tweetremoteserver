package com.company.tweetremote.model;

/**
 * Created by Sunil on 14/02/2017.
 */
public class UserFollowDTO {
    private String followerUserLogin;
    private String followingUserLogin;

    public String getFollowerUserLogin() {
        return followerUserLogin;
    }

    public void setFollowerUserLogin(String followerUserLogin) {
        this.followerUserLogin = followerUserLogin;
    }

    public String getFollowingUserLogin() {
        return followingUserLogin;
    }

    public void setFollowingUserLogin(String followingUserLogin) {
        this.followingUserLogin = followingUserLogin;
    }

    @Override
    public String toString() {
        return "UserFollowDTO{" +
                "followerUserLogin='" + followerUserLogin + '\'' +
                ", followingUserLogin='" + followingUserLogin + '\'' +
                '}';
    }
}
