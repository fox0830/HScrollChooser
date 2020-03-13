package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class ForgetPwdReq {

    String lc_id;
    String lc_pwd;
    String lc_pwd_confirm;

    public ForgetPwdReq(String lc_id, String lc_pwd, String lc_pwd_confirm) {
        this.lc_id = lc_id;
        this.lc_pwd = lc_pwd;
        this.lc_pwd_confirm = lc_pwd_confirm;
    }
}
