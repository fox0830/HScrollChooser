package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class BindThirdReq extends BasicReq {
    String platform;
    String platform_id;

    public BindThirdReq(String platform, String platform_id) {
        this.platform = platform;
        this.platform_id = platform_id;
    }
}
