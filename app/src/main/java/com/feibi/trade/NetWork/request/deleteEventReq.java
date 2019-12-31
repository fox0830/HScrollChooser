package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class deleteEventReq extends BasicReq {
    String event_id;

    public deleteEventReq(String event_id) {
        this.event_id = event_id;
    }
}
