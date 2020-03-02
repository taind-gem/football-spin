package com.tafi.footballspin.data.db.model;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by taind-201 on 2/16/2020.
 */

@Entity(nameInDb = "team")
public class Team {

    @Id
    @SerializedName("id")
    @Property(nameInDb = "id")
    public Long id;

    @SerializedName("key")
    @Property(nameInDb = "key")
    public String key;

    @SerializedName("name")
    @Property(nameInDb = "name")
    public String name;

    @SerializedName("abbr")
    @Property(nameInDb = "abbr")
    public String abbr;

    @SerializedName("code")
    @Property(nameInDb = "code")
    public String code;

    @SerializedName("league_name")
    @Property(nameInDb = "league_name")
    public String leagueName;

    @SerializedName("league_code")
    @Property(nameInDb = "league_code")
    public String leagueCode;

    @SerializedName("rate")
    @Property(nameInDb = "rate")
    public Float rate;

    @Generated(hash = 572574055)
    public Team(Long id, String key, String name, String abbr, String code,
            String leagueName, String leagueCode, Float rate) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.abbr = abbr;
        this.code = code;
        this.leagueName = leagueName;
        this.leagueCode = leagueCode;
        this.rate = rate;
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

}
