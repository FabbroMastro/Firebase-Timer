package com.francesco.domotica.app.model;

public class FirebaseTimer
{
    // Attributes
    private int enddate;
    private boolean isRunning;
    private int startdate;
    
    // Constructors
    public FirebaseTimer() {}

    public FirebaseTimer(int enddate, boolean isRunning, int startdate) {
        this.enddate = enddate;
        this.isRunning = isRunning;
        this.startdate = startdate;
    }
    public int getEnddate() {
        return enddate;
    }
    public void setEnddate(int enddate) {
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

