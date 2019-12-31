package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class ChangeHostNameReq extends BasicReq {
    String host_id;
    String host_name;

    public String getHost_id() {
        return host_id;
    }

    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public ChangeHostNameReq(String host_id, String host_name) {
        this.host_id = host_id;
        this.host_name = host_name;
    }
}
