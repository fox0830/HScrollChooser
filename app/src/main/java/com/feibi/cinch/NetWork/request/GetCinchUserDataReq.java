package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class GetCinchUserDataReq {
        String mb_no;
        String lc_id;

    public GetCinchUserDataReq(String mb_no, String lc_id) {
        this.mb_no = mb_no;
        this.lc_id = lc_id;
    }
}
