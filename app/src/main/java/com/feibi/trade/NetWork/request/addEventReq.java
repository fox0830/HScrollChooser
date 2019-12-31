package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class addEventReq extends BasicReq {
    String device_id;
    String event_name;
    String repeat;
    String time_start;
    String time_end;
    String time_status;
    String temperature_limit;
    String temperature_limit_value;
    String temperature_status;
    String temperature_value;
    String humidity_limit;
    String humidity_limit_value;
    String humidity_status;
    String humidity_value;
    String air_limit;
    String air_limit_value;
    String air_status;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTime_status() {
        return time_status;
    }

    public void setTime_status(String time_status) {
        this.time_status = time_status;
    }

    public String getTemperature_limit() {
        return temperature_limit;
    }

    public void setTemperature_limit(String temperature_limit) {
        this.temperature_limit = temperature_limit;
    }

    public String getTemperature_limit_value() {
        return temperature_limit_value;
    }

    public void setTemperature_limit_value(String temperature_limit_value) {
        this.temperature_limit_value = temperature_limit_value;
    }

    public String getTemperature_status() {
        return temperature_status;
    }

    public void setTemperature_status(String temperature_status) {
        this.temperature_status = temperature_status;
    }

    public String getTemperature_value() {
        return temperature_value;
    }

    public void setTemperature_value(String temperature_value) {
        this.temperature_value = temperature_value;
    }

    public String getHumidity_limit() {
        return humidity_limit;
    }

    public void setHumidity_limit(String humidity_limit) {
        this.humidity_limit = humidity_limit;
    }

    public String getHumidity_limit_value() {
        return humidity_limit_value;
    }

    public void setHumidity_limit_value(String humidity_limit_value) {
        this.humidity_limit_value = humidity_limit_value;
    }

    public String getHumidity_status() {
        return humidity_status;
    }

    public void setHumidity_status(String humidity_status) {
        this.humidity_status = humidity_status;
    }

    public String getHumidity_value() {
        return humidity_value;
    }

    public void setHumidity_value(String humidity_value) {
        this.humidity_value = humidity_value;
    }

    public String getAir_limit() {
        return air_limit;
    }

    public void setAir_limit(String air_limit) {
        this.air_limit = air_limit;
    }

    public String getAir_limit_value() {
        return air_limit_value;
    }

    public void setAir_limit_value(String air_limit_value) {
        this.air_limit_value = air_limit_value;
    }

    public String getAir_status() {
        return air_status;
    }

    public void setAir_status(String air_status) {
        this.air_status = air_status;
    }
}
