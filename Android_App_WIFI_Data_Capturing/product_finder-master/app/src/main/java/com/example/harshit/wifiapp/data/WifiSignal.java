package com.example.harshit.wifiapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class WifiSignal {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String ssid1;
    private String ssid2;
    private String ssid3;
    private String ssid4;
    private int level1;
    private int level2;
    private int level3;
    private int level4;
    private int grid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSsid1() {
        return ssid1;
    }

    public void setSsid1(String ssid1) {
        this.ssid1 = ssid1;
    }

    public String getSsid2() {
        return ssid2;
    }

    public void setSsid2(String ssid2) {
        this.ssid2 = ssid2;
    }

    public String getSsid3() {
        return ssid3;
    }

    public void setSsid3(String ssid3) {
        this.ssid3 = ssid3;
    }

    public String getSsid4() {
        return ssid4;
    }

    public void setSsid4(String ssid4) {
        this.ssid4 = ssid4;
    }

    public int getLevel1() {
        return level1;
    }

    public void setLevel1(int level1) {
        this.level1 = level1;
    }

    public int getLevel2() {
        return level2;
    }

    public void setLevel2(int level2) {
        this.level2 = level2;
    }

    public int getLevel3() {
        return level3;
    }

    public void setLevel3(int level3) {
        this.level3 = level3;
    }

    public int getLevel4() {
        return level4;
    }

    public void setLevel4(int level4) {
        this.level4 = level4;
    }

    public int getGrid() {
        return grid;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    @Override
    public String toString() {
        return "WifiSignal{" +
                "grid=" + grid +
                ", level0=" + level1 +
                ", level1=" + level2 +
                ", level2=" + level3 +
                ", level3=" + level4 +
                '}';
    }
}
