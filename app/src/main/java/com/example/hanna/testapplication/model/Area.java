package com.example.hanna.testapplication.model;

/**
 * Created by hanna on 3/13/15.
 */
public class Area {
    private String guid;
    private String name;
    private String latlon;

    public String[] getLatlon() {
        return latlon.split(",",2);
    }

    public void setLatlon(String latlon) {
        this.latlon = latlon;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getName() {
        return this.name;
    }
}
