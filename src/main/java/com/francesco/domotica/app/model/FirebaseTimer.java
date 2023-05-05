package com.francesco.domotica.app.model;

public class FirebaseTimer
{
    // Attributes
    private long enddate;
    private boolean isRunning;
    private int startdate;
    
    // Constructors
    public FirebaseTimer() {}

    public FirebaseTimer(long enddate, boolean isRunning, int startdate) {
        this.enddate = enddate;
        this.isRunning = isRunning;
        this.startdate = startdate;
    }
    public long getEnddate() {
        return enddate;
    }
    public void setEnddate(long enddate) {
        this.enddate = enddate;
    }
    public boolean getisRunning() {
        return isRunning;
    }
    public void setisRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    public int getStartdate() {
        return startdate;
    }
    public void setStartdate(int startdate) {
        this.startdate = startdate;
    }   
}

