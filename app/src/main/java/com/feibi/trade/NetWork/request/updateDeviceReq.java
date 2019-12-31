package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class updateDeviceReq extends BasicReq {
    String device_id;
    String data_name;
    String data_value;

    public updateDeviceReq(String device_id, String data_name, String data_value) {
        this.device_id = device_id;
        this.data_name = data_name;
        this.data_value = data_value;
    }
}
