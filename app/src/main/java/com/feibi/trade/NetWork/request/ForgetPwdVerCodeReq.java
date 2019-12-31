package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class ForgetPwdVerCodeReq extends BasicReq {
    String email;
    String code;

    public ForgetPwdVerCodeReq(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
