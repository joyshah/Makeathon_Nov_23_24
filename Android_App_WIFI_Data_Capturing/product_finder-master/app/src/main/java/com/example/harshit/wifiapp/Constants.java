package com.example.harshit.wifiapp;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Constants {
    public static final String device1 = "Air 2.0";
    public static final String device2 = "Air 2.1";
    public static final String device3 = "Air 2.2";
    public static final String device4 = "Air 2.3";

    public Set<String> devices;

    public Constants() {
        devices = new HashSet<>();
        devices.add(device1);
        devices.add(device2);
        devices.add(device3);
        devices.add(device4);
    }
}
