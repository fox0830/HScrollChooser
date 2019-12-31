package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class ReadMsgReq extends BasicReq {
    String msg_id;

    public ReadMsgReq(String msg_id) {
        this.msg_id = msg_id;
    }
}
