package com.feibi.trade.NetWork.respond;

public class getEnvironmentRes {

    /**
     * humidity : null
     * co2 : null
     * pm : null
     * temperature : null
     * flow : null
     * quality : null
     * address : 6M2Q+93 中国江苏省南京市浦口区
     */

    private String humidity;
    private String co2;
    private String pm;
    private String temperature;
    private String flow;
    private String quality;
    private String address;

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
