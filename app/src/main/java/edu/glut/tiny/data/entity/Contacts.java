package edu.glut.tiny.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * 通讯录里的好友实体
 **/

@Entity
public class Contacts implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long cid;
    private String ContactsFriendUsername;

    public Contacts() {
    }

    public Contacts(String contactsFriendUsername) {
        ContactsFriendUsername = contactsFriendUsername;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getContactsFriendUsername() {
        return ContactsFriendUsername;
    }

    public void setContactsFriendUsername(String contactsFriendUsername) {
        ContactsFriendUsername = contactsFriendUsername;
    }
}
