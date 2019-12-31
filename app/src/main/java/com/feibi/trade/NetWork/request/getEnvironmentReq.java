package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class getEnvironmentReq extends BasicReq {
    String lat;
    String lng;

    public getEnvironmentReq(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
