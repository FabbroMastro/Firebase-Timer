package com.francesco.domotica.app.model;

public class Timer {

    private int enddate;
    private boolean isRunning;
    private int startdate;
    
    public Timer(int startdate, int enddate, boolean isRunning) {
        this.startdate = startdate;
        this.enddate = enddate;
        this.isRunning = isRunning;
    }

    // Getters
    public int getStartdate() {
        return startdate;
    }

    public int getEnddate() {
        return enddate;
    }

    public boolean isRunning() {
        return isRunning;
    }

    // Setters
    public void setStartdate(int startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(int enddate) {
        this.enddate = enddate;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

}
