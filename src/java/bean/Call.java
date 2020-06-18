package bean;

import java.io.Serializable;

public class Call implements Serializable{
    private String phoneNumber;
    private String callTime;
    private int duration;
    private String URL;
    private String incidentType;

    public Call(String phoneNumber, String callTime, int duration, String URL, String incidentType) {
        this.phoneNumber = phoneNumber;
        this.callTime = callTime;
        this.duration = duration;
        this.URL = URL;
        this.incidentType = incidentType;
    }
    
    public Call() {
        this.phoneNumber = "";
        this.callTime = "";
        this.duration = 0;
        this.URL = "";
        this.incidentType = "";
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }
}
