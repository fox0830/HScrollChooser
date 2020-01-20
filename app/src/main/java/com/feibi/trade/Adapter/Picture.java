package com.feibi.trade.Adapter;

public class Picture {
    private String path;
    private boolean isChoose;
    float latitude;
    float longitude;

    public Picture(String path, float latitude, float longitude) {
        this.path = path;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public void initChoose() {
        isChoose = false;
    }

    public void switchChoose() {
        isChoose = !isChoose;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
