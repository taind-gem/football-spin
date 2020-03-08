package com.tafi.footballspin.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by taind-201 on 3/1/2020.
 */

@Entity(nameInDb = "statistic")
public class Statistic {

    @Id
    public Long id;

    public Long hostScore;

    public Long guestScore;

    public Long red;

    public Long yellow;

    @Generated(hash = 799127015)
    public Statistic(Long id, Long hostScore, Long guestScore, Long red,
                     Long yellow) {
        this.id = id;
        this.hostScore = hostScore;
        this.guestScore = guestScore;
        this.red = red;
        this.yellow = yellow;
    }

    @Generated(hash = 754849185)
    public Statistic() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHostScore() {
        return this.hostScore;
    }

    public void setHostScore(Long hostScore) {
        this.hostScore = hostScore;
    }

    public Long getGuestScore() {
        return this.guestScore;
    }

    public void setGuestScore(Long guestScore) {
        this.guestScore = guestScore;
    }

    public Long getRed() {
        return this.red;
    }

    public void setRed(Long red) {
        this.red = red;
    }

    public Long getYellow() {
        return this.yellow;
    }

    public void setYellow(Long yellow) {
        this.yellow = yellow;
    }

}