package com.tafi.footballspin.data.db.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

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

    @SerializedName("is_join")
    @Property(nameInDb = "is_join")
    private Boolean isJoin;

    @SerializedName("team_ids")
    @Property(nameInDb = "team_ids")
    private String teamIds;

    @Transient
    public List<Team> listTeam;

    public Player(String username, String nickname, String avatarUrl) {
        this.username = username;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }

    @Generated(hash = 1943544659)
    public Player(Long id, String username, String nickname, String avatarUrl,
            Boolean isJoin, String teamIds) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
        this.isJoin = isJoin;
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

    public void setTeamIds(String teamIds) {
        this.teamIds = teamIds;
    }

    public Boolean getIsJoin() {
        return this.isJoin;
    }

    public void setIsJoin(Boolean isJoin) {
        this.isJoin = isJoin;
    }

    public List<Long> getListTeamIdSelected() {
        Gson gson = new Gson();
        if (this.teamIds == null) return null;
        return Arrays.asList(gson.fromJson(this.teamIds, Long[].class));
    }

    public String convertPlayerToString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public Player converStringToPlayer(String str){
        Gson gson = new Gson();
        return gson.fromJson(str, Player.class);
    }
}
