package com.tafi.footballspin.data.db.model;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by taind-201 on 2/10/2020.
 */

@Entity(nameInDb = "player")
public class Player {

    @Id(autoincrement = true)
    @SerializedName("id")
    @Property(nameInDb = "id")
    private Long id;

    @SerializedName("username")
    @Property(nameInDb = "username")
    private String username;

    @SerializedName("nickname")
    @Property(nameInDb = "nickname")
    private String nickname;

    @SerializedName("avatar_url")
    @Property(nameInDb = "avatar_url")
    private String avatarUrl;

    public Player(String username, String nickname, String avatarUrl) {
        this.username = username;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }

    @Generated(hash = 1495284834)
    public Player(Long id, String username, String nickname, String avatarUrl) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }

    @Generated(hash = 30709322)
    public Player() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
