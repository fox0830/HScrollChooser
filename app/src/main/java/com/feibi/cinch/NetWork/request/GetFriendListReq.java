package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;


public class GetFriendListReq {
    String on_status;  //o-old，n-new
    String mb_no;

    public GetFriendListReq(String on_status, String mb_no) {
        this.on_status = on_status;
        this.mb_no = mb_no;
    }
}
