package com.tafi.footballspin.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by taind-201 on 2/16/2020.
 */

@Entity(nameInDb = "team")
public class Team {

    @Id
    public Long id;

    public String key;

    public String name;

    public String abbr;

    public String code;

    public String leagueName;

    public String leagueCode;

    public Float rate;

    public Boolean isPlayed;

    @Generated(hash = 287084042)
    public Team(Long id, String key, String name, String abbr, String code,
            String leagueName, String leagueCode, Float rate, Boolean isPlayed) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.abbr = abbr;
        this.code = code;
        this.leagueName = leagueName;
        this.leagueCode = leagueCode;
        this.rate = rate;
        this.isPlayed = isPlayed;
    }

    @Generated(hash = 882286361)
    public Team() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return this.abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLeagueName() {
        return this.leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueCode() {
        return this.leagueCode;
    }

    public void setLeagueCode(String leagueCode) {
        this.leagueCode = leagueCode;
    }

    public Float getRate() {
        return this.rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Boolean getIsPlayed() {
        return this.isPlayed;
    }

    public void setIsPlayed(Boolean isPlayed) {
        this.isPlayed = isPlayed;
    }

}
