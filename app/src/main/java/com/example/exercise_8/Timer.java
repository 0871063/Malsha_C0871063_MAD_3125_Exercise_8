package com.example.exercise_8;

import java.util.ArrayList;

public class Timer {
    private String lapCount;
    private String time;

    public String getLapCount() {
        return lapCount;
    }

    public void setLapCount(String lapCount) {
        this.lapCount = lapCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Timer(String lapCount, String time) {
        this.lapCount = lapCount;
        this.time = time;
    }

    public static ArrayList<Timer> timerList = new ArrayList<>();
}
