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

    @Id
    @SerializedName("id")
    @Property(nameInDb = "id")
    private Long id;

    @SerializedName("username")
    @Property(nameInDb = "username")
    private String username;

    @SerializedName("nickname")
    @Property(nameInDb = "nickname")
    private String nickname;

    @SerializedName("email")
    @Property(nameInDb = "email")
    private String email;

    @SerializedName("birthday")
    @Property(nameInDb = "birthday")
    private String birthday;

    @SerializedName("phone")
    @Property(nameInDb = "phone")
    private String phone;

    @SerializedName("sex")
    @Property(nameInDb = "sex")
    private int sex;

    @Generated(hash = 994565009)
    public Player(Long id, String username, String nickname, String email,
            String birthday, String phone, int sex) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.sex = sex;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

}
