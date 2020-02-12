package com.tafi.footballspin.data.db.model;

import com.google.gson.annotations.SerializedName;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by taind-201 on 2/12/2020.
 */

@Entity(nameInDb = "match")
public class Match {

    @Id
    @SerializedName("match_id")
    @Property(nameInDb = "match_id")
    private Long matchId;

    @SerializedName("host_id")
    @Property(nameInDb = "host_id")
    private Long hostId;

    @SerializedName("guest_id")
    @Property(nameInDb = "guest_id")
    private Long guestId;

    @SerializedName("score")
    @Property(nameInDb = "score")
    private String score;

    @Generated(hash = 1827855482)
    public Match(Long matchId, Long hostId, Long guestId, String score) {
        this.matchId = matchId;
        this.hostId = hostId;
        this.guestId = guestId;
        this.score = score;
    }

    @Generated(hash = 1834681287)
    public Match() {
    }

    public Long getMatchId() {
        return this.matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getHostId() {
        return this.hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public Long getGuestId() {
        return this.guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}