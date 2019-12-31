package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class EditDeviceReq  extends BasicReq {
    String device_id;
    String device_name;
    String device_place_id;      //更改地點
    String device_place_name;    //新增一個地點

    public EditDeviceReq(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_place_id() {
        return device_place_id;
    }

    public void setDevice_place_id(String device_place_id) {
        this.device_place_id = device_place_id;
    }

    public String getDevice_place_name() {
        return device_place_name;
    }

    public void setDevice_place_name(String device_place_name) {
        this.device_place_name = device_place_name;
    }
}
