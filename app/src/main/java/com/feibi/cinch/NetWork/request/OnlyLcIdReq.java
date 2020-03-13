package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;


public class OnlyLcIdReq {
    String lc_id;

    public OnlyLcIdReq(String lc_id) {
        this.lc_id = lc_id;
    }
}
