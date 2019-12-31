package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class ForgetPwdSetPwdReq extends BasicReq {
    String email;
    String code;
    String password;
    String code_token;

    public ForgetPwdSetPwdReq(String email, String code, String password, String code_token) {
        this.email = email;
        this.code = code;
        this.password = password;
        this.code_token = code_token;
    }
}
