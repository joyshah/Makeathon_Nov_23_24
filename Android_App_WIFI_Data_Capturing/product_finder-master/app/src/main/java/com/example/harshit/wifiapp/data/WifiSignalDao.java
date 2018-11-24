package com.example.harshit.wifiapp.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WifiSignalDao {

    @Insert
    void insert(WifiSignal signal);

    @Query("SELECT * FROM wifisignal")
    List<WifiSignal> getAll();
}
