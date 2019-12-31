package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class GetMsgListReq extends BasicReq {
    int page;
    int limit;
    String msg_type;

    public GetMsgListReq() {
    }

    public GetMsgListReq(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public GetMsgListReq(int page, int limit, String msg_type) {
        this.page = page;
        this.limit = limit;
        this.msg_type = msg_type;
    }
}
