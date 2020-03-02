package com.tafi.footballspin.data.db.model;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by taind-201 on 3/1/2020.
 */

@Entity(nameInDb = "statistic")
public class Statistic {

    @Id
    @SerializedName("id")
    @Property(nameInDb = "id")
    private Long id;

    @SerializedName("host_score")
    @Property(nameInDb = "host_score")
    private Long hostScore;

    @SerializedName("guest_score")
    @Property(nameInDb = "guest_score")
    private Long guest_score;

    @SerializedName("host_red")
    @Property(nameInDb = "host_red")
    private Long hostRed;

    @SerializedName("guest_red")
    @Property(nameInDb = "guest_red")
    private Long guestRed;

    @SerializedName("host_yellow")
    @Property(nameInDb = "host_yellow")
    private Long hostYellow;

    @SerializedName("guest_yellow")
    @Property(nameInDb = "guest_yellow")
    private Long guestYellow;

    @Generated(hash = 968863917)
    public Statistic(Long id, Long hostScore, Long guest_score, Long hostRed,
            Long guestRed, Long hostYellow, Long guestYellow) {
        this.id = id;
        this.hostScore = hostScore;
        this.guest_score = guest_score;
        this.hostRed = hostRed;
        this.guestRed = guestRed;
        this.hostYellow = hostYellow;
        this.guestYellow = guestYellow;
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

    public Long getGuest_score() {
        return this.guest_score;
    }

    public void setGuest_score(Long guest_score) {
        this.guest_score = guest_score;
    }

    public Long getHostRed() {
        return this.hostRed;
    }

    public void setHostRed(Long hostRed) {
        this.hostRed = hostRed;
    }

    public Long getGuestRed() {
        return this.guestRed;
    }

    public void setGuestRed(Long guestRed) {
        this.guestRed = guestRed;
    }

    public Long getHostYellow() {
        return this.hostYellow;
    }

    public void setHostYellow(Long hostYellow) {
        this.hostYellow = hostYellow;
    }

    public Long getGuestYellow() {
        return this.guestYellow;
    }

    public void setGuestYellow(Long guestYellow) {
        this.guestYellow = guestYellow;
    }

}