package com.tafi.footballspin.data.db.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Arrays;
import java.util.List;

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

    @SerializedName("team_ids")
    @Property(nameInDb = "team_ids")
    private String teamIds;

    public Player(String username, String nickname, String avatarUrl) {
        this.username = username;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }

    @Generated(hash = 775948011)
    public Player(Long id, String username, String nickname, String avatarUrl,
            String teamIds) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
        this.teamIds = teamIds;
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

    public String getTeamIds() {
        return this.teamIds;
    }

    public void setTeamIds(List<Integer> listId) {
        Gson gson = new Gson();
        this.teamIds = gson.toJson(listId);
    }

    public List<Integer> getTeamSelected() {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(this.teamIds, Integer[].class));
    }

    public void setTeamIds(String teamIds) {
        this.teamIds = teamIds;
    }

}
