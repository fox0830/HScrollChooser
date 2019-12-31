package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class deleteHostReq extends BasicReq {
    String host_id;

    public deleteHostReq(String host_id) {
        this.host_id = host_id;
    }
}
