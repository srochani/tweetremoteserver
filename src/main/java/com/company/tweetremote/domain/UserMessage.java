package com.company.tweetremote.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sunil on 13/02/2017.
 * Entity to hold information of messages by User
 */
@Entity
public class UserMessage {

    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private String message;
    private Date dateCreated;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "Id=" + Id +
                ", user=" + user +
                ", message='" + message + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
