package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class DeviceBindReq extends BasicReq {
    String thing;
//    String thing_data;

    public DeviceBindReq(String thing) {
        this.thing = thing;
//        this.thing_data = thing_data;
    }
}
