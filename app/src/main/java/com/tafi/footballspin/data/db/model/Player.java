package com.tafi.footballspin.data.db.model;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by taind-201 on 2/10/2020.
 */

@Entity(nameInDb = "player")
public class Player {

    @Id(autoincrement = true)
    @SerializedName("id")
    @Property(nameInDb = "id")
    private Long id;

    @SerializedName("name")
    @Property(nameInDb = "name")
    private String name;

    @SerializedName("nickname")
    @Property(nameInDb = "nickname")
    private String nickname;

    @SerializedName("avatar_url")
    @Property(nameInDb = "avatar_url")
    private String avatarUrl;

    @Generated(hash = 1829865680)
    public Player(Long id, String name, String nickname, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }

    @Generated(hash = 30709322)
    public Player() {
    }

    public Player(String name, String nickname, String avatarUrl) {
        this.name = name;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
