package com.tafi.footballspin.data.db.model;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
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

    @SerializedName("host_player_id")
    @Property(nameInDb = "host_player_id")
    private Long hostPlayerId;

    @SerializedName("guest_player_id")
    @Property(nameInDb = "guest_player_id")
    private Long guestPlayerId;

    @SerializedName("host_team_id")
    @Property(nameInDb = "host_team_id")
    private Long hostTeamId;

    @SerializedName("guest_team_id")
    @Property(nameInDb = "guest_team_id")
    private Long guestTeamId;
    
    @SerializedName("statistic_id")
    @Property(nameInDb = "statistic_id")
    private Long statisticId;

    @Transient
    public Team hostPlayer;

    @Transient
    public Team guestPlayer;

    @Transient
    public Team hostTeam;

    @Transient
    public Team guestTeam;

    @Transient
    public Statistic statistic;

    @Generated(hash = 859926195)
    public Match(Long matchId, Long hostPlayerId, Long guestPlayerId,
            Long hostTeamId, Long guestTeamId, Long statisticId) {
        this.matchId = matchId;
        this.hostPlayerId = hostPlayerId;
        this.guestPlayerId = guestPlayerId;
        this.hostTeamId = hostTeamId;
        this.guestTeamId = guestTeamId;
        this.statisticId = statisticId;
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

    public Long getHostPlayerId() {
        return this.hostPlayerId;
    }

    public void setHostPlayerId(Long hostPlayerId) {
        this.hostPlayerId = hostPlayerId;
    }

    public Long getGuestPlayerId() {
        return this.guestPlayerId;
    }

    public void setGuestPlayerId(Long guestPlayerId) {
        this.guestPlayerId = guestPlayerId;
    }

    public Long getHostTeamId() {
        return this.hostTeamId;
    }

    public void setHostTeamId(Long hostTeamId) {
        this.hostTeamId = hostTeamId;
    }

    public Long getGuestTeamId() {
        return this.guestTeamId;
    }

    public void setGuestTeamId(Long guestTeamId) {
        this.guestTeamId = guestTeamId;
    }

    public Long getStatisticId() {
        return this.statisticId;
    }

    public void setStatisticId(Long statisticId) {
        this.statisticId = statisticId;
    }

}