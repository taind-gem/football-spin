package com.tafi.footballspin.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by taind-201 on 2/12/2020.
 */

@Entity(nameInDb = "match")
public class Match {

    @Id
    private Long matchId;

    private Long createdTime;

    private Long winPoint;

    private Long drawPoint;

    private Long hostPlayerId;
    @ToOne(joinProperty = "hostPlayerId")
    private Player hostPlayer;

    private Long guestPlayerId;
    @ToOne(joinProperty = "guestPlayerId")
    private Player guestPlayer;

    private Long hostTeamId;
    @ToOne(joinProperty = "hostTeamId")
    private Team hostTeam;

    private Long guestTeamId;
    @ToOne(joinProperty = "guestTeamId")
    private Team guestTeam;

    private Long statisticId;
    @ToOne(joinProperty = "statisticId")
    private Statistic statistic;

    public Long getMatchId() {
        return this.matchId;
    }
    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
    public Long getCreatedTime() {
        return this.createdTime;
    }
    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
    public Long getWinPoint() {
        return this.winPoint;
    }
    public void setWinPoint(Long winPoint) {
        this.winPoint = winPoint;
    }
    public Long getDrawPoint() {
        return this.drawPoint;
    }
    public void setDrawPoint(Long drawPoint) {
        this.drawPoint = drawPoint;
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

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 522467795)
    private transient MatchDao myDao;
    @Generated(hash = 232973977)
    public Match(Long matchId, Long createdTime, Long winPoint, Long drawPoint,
            Long hostPlayerId, Long guestPlayerId, Long hostTeamId,
            Long guestTeamId, Long statisticId) {
        this.matchId = matchId;
        this.createdTime = createdTime;
        this.winPoint = winPoint;
        this.drawPoint = drawPoint;
        this.hostPlayerId = hostPlayerId;
        this.guestPlayerId = guestPlayerId;
        this.hostTeamId = hostTeamId;
        this.guestTeamId = guestTeamId;
        this.statisticId = statisticId;
    }
    @Generated(hash = 1834681287)
    public Match() {
    }
    @Generated(hash = 935829805)
    private transient Long hostPlayer__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2070117749)
    public Player getHostPlayer() {
        Long __key = this.hostPlayerId;
        if (hostPlayer__resolvedKey == null
                || !hostPlayer__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlayerDao targetDao = daoSession.getPlayerDao();
            Player hostPlayerNew = targetDao.load(__key);
            synchronized (this) {
                hostPlayer = hostPlayerNew;
                hostPlayer__resolvedKey = __key;
            }
        }
        return hostPlayer;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2016739102)
    public void setHostPlayer(Player hostPlayer) {
        synchronized (this) {
            this.hostPlayer = hostPlayer;
            hostPlayerId = hostPlayer == null ? null : hostPlayer.getId();
            hostPlayer__resolvedKey = hostPlayerId;
        }
    }
    @Generated(hash = 1673868391)
    private transient Long guestPlayer__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1517938365)
    public Player getGuestPlayer() {
        Long __key = this.guestPlayerId;
        if (guestPlayer__resolvedKey == null
                || !guestPlayer__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlayerDao targetDao = daoSession.getPlayerDao();
            Player guestPlayerNew = targetDao.load(__key);
            synchronized (this) {
                guestPlayer = guestPlayerNew;
                guestPlayer__resolvedKey = __key;
            }
        }
        return guestPlayer;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 236472730)
    public void setGuestPlayer(Player guestPlayer) {
        synchronized (this) {
            this.guestPlayer = guestPlayer;
            guestPlayerId = guestPlayer == null ? null : guestPlayer.getId();
            guestPlayer__resolvedKey = guestPlayerId;
        }
    }
    @Generated(hash = 2105440713)
    private transient Long hostTeam__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1377124522)
    public Team getHostTeam() {
        Long __key = this.hostTeamId;
        if (hostTeam__resolvedKey == null || !hostTeam__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeamDao targetDao = daoSession.getTeamDao();
            Team hostTeamNew = targetDao.load(__key);
            synchronized (this) {
                hostTeam = hostTeamNew;
                hostTeam__resolvedKey = __key;
            }
        }
        return hostTeam;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 330549351)
    public void setHostTeam(Team hostTeam) {
        synchronized (this) {
            this.hostTeam = hostTeam;
            hostTeamId = hostTeam == null ? null : hostTeam.getId();
            hostTeam__resolvedKey = hostTeamId;
        }
    }
    @Generated(hash = 578573896)
    private transient Long guestTeam__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1940999282)
    public Team getGuestTeam() {
        Long __key = this.guestTeamId;
        if (guestTeam__resolvedKey == null
                || !guestTeam__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeamDao targetDao = daoSession.getTeamDao();
            Team guestTeamNew = targetDao.load(__key);
            synchronized (this) {
                guestTeam = guestTeamNew;
                guestTeam__resolvedKey = __key;
            }
        }
        return guestTeam;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 862932939)
    public void setGuestTeam(Team guestTeam) {
        synchronized (this) {
            this.guestTeam = guestTeam;
            guestTeamId = guestTeam == null ? null : guestTeam.getId();
            guestTeam__resolvedKey = guestTeamId;
        }
    }
    @Generated(hash = 19134593)
    private transient Long statistic__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1301145812)
    public Statistic getStatistic() {
        Long __key = this.statisticId;
        if (statistic__resolvedKey == null
                || !statistic__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StatisticDao targetDao = daoSession.getStatisticDao();
            Statistic statisticNew = targetDao.load(__key);
            synchronized (this) {
                statistic = statisticNew;
                statistic__resolvedKey = __key;
            }
        }
        return statistic;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 989503337)
    public void setStatistic(Statistic statistic) {
        synchronized (this) {
            this.statistic = statistic;
            statisticId = statistic == null ? null : statistic.getId();
            statistic__resolvedKey = statisticId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 88911878)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMatchDao() : null;
    }
}