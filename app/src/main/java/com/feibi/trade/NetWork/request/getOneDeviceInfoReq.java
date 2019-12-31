package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class getOneDeviceInfoReq extends BasicReq {
    String device_id;

    public getOneDeviceInfoReq(String device_id) {
        this.device_id = device_id;
    }
}
